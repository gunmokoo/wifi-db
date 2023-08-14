package db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class JsonTest {

	public JsonTest() {
	}

	public int dbInsert() {
		int cnt = 0;
		String key = "634b71424867756e3139446479574b";

		String dbUrl = "jdbc:mariadb://localhost:3306/wifidb";
		String dbUserId = "wifiuser";
		String dbPassword = "zerobase";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException var26) {
			throw new RuntimeException(var26);
		}

		for (int i = 1; i <= 15; i++) {
			int end = i * 1000;
			int start = end - 999;
			String wifiUrl = "http://openapi.seoul.go.kr:8088/" + key + "/json/TbPublicWifiInfo/" + start + "/" + end
					+ "/";

			URL url = null;
			HttpURLConnection con = null;
			JSONObject result = null;
			StringBuilder sb = new StringBuilder();

			try {
				url = new URL(wifiUrl);
				con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Content-type", "application/json");
				con.setDoOutput(true);

				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				while (br.ready()) {
					sb.append(br.readLine());
				}
				con.disconnect();
				result = (JSONObject) new JSONParser().parse(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONObject data = (JSONObject) result.get("TbPublicWifiInfo");
			JSONArray arr = (JSONArray) data.get("row");

			JSONObject tmp = null;
			for (int j = 0; j < arr.size(); j++) {
				tmp = (JSONObject) arr.get(j);

				Connection connection = null;
				PreparedStatement preparedStatement = null;

				try {
					connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);
					String sql = " insert into WIFI_DB (number, borough, name, street_address, "
							+ " detail_address, installation_location, installation_type, installation_agency, service, "
							+ " net, year_of_installation, in_and_out, connection_environment, x_coordinate, "
							+ " y_coordinate, working_date) "
							+ " values(? , ? ,?, ? , ? ,?, ? , ? ,?, ? , ? ,?, ? , ?, ?, ?); ";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, (String) tmp.get("X_SWIFI_MGR_NO"));
					preparedStatement.setString(2, (String) tmp.get("X_SWIFI_WRDOFC"));
					preparedStatement.setString(3, (String) tmp.get("X_SWIFI_MAIN_NM"));
					preparedStatement.setString(4, (String) tmp.get("X_SWIFI_ADRES1"));
					preparedStatement.setString(5, (String) tmp.get("X_SWIFI_ADRES2"));
					preparedStatement.setString(6, (String) tmp.get("X_SWIFI_INSTL_FLOOR"));
					preparedStatement.setString(7, (String) tmp.get("X_SWIFI_INSTL_TY"));
					preparedStatement.setString(8, (String) tmp.get("X_SWIFI_INSTL_MBY"));
					preparedStatement.setString(9, (String) tmp.get("X_SWIFI_SVC_SE"));
					preparedStatement.setString(10, (String) tmp.get("X_SWIFI_CMCWR"));
					preparedStatement.setString(11, (String) tmp.get("X_SWIFI_CNSTC_YEAR"));
					preparedStatement.setString(12, (String) tmp.get("X_SWIFI_INOUT_DOOR"));
					preparedStatement.setString(13, (String) tmp.get("X_SWIFI_REMARS3"));
					preparedStatement.setDouble(14, Double.parseDouble((String) tmp.get("LNT")));
					preparedStatement.setDouble(15, Double.parseDouble((String) tmp.get("LAT")));

					Date date = null;
					String str_date = (String) tmp.get("WORK_DTTM");
					DateFormat formatter;
					formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.0");
					try {
						date = (Date) formatter.parse(str_date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					preparedStatement.setDate(16, sqlDate);

					int affected = preparedStatement.executeUpdate();
					if (affected > 0) {
						cnt++;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (preparedStatement != null && !preparedStatement.isClosed()) {
							preparedStatement.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						if (connection != null && !connection.isClosed()) {
							connection.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return cnt;
	}

	public ArrayList<Wifi> dbUpdate(double x, double y) {
		ArrayList<Wifi> list = new ArrayList<>();

		String dbUrl = "jdbc:mariadb://localhost:3306/wifidb";
		String dbUserId = "wifiuser";
		String dbPassword = "zerobase";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException var26) {
			throw new RuntimeException(var26);
		}

		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement historyPreparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);
			String wifiSql = " update WIFI_DB set "
					+ " distance = SQRT(power(WIFI_DB.x_coordinate - ?, 2) + power(WIFI_DB.y_coordinate - ?,2)); ";
			preparedStatement = connection.prepareStatement(wifiSql);
			preparedStatement.setDouble(1, x);
			preparedStatement.setDouble(2, y);

			int affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				String historySql = " insert into HISTORY(x_coordinate, y_coordinate, check_date) "
						+ "values (?, ? , ?); ";
				historyPreparedStatement = connection.prepareStatement(historySql);
				historyPreparedStatement.setDouble(1, x);
				historyPreparedStatement.setDouble(2, y);
				Date now = new Date();
				java.sql.Timestamp ts = new java.sql.Timestamp(now.getTime());
				historyPreparedStatement.setTimestamp(3, ts);
				int affected2 = historyPreparedStatement.executeUpdate();
				
				if(affected2 > 0) {
					statement = connection.createStatement();
					String sSql = " SELECT * " + " FROM WIFI_DB ORDER BY distance asc limit 20; ";
					rs = statement.executeQuery(sSql);
					while (rs.next()) {
						Wifi wifi = new Wifi();

						wifi.setDistance(rs.getDouble("distance"));
						wifi.setNumber(rs.getString("number"));
						wifi.setBorough(rs.getString("borough"));
						wifi.setName(rs.getString("name"));
						wifi.setStreet_address(rs.getString("street_address"));

						wifi.setDetail_address(rs.getString("detail_address"));
						wifi.setInstallation_location(rs.getString("installation_location"));
						wifi.setInstallation_type(rs.getString("installation_type"));
						wifi.setInstallation_agency(rs.getString("installation_agency"));
						wifi.setService(rs.getString("service"));

						wifi.setNet(rs.getString("net"));
						wifi.setYear_of_installation(rs.getInt("year_of_installation"));
						wifi.setIn_and_out(rs.getString("in_and_out"));
						wifi.setConnection_environment(rs.getString("connection_environment"));
						wifi.setX_coordinate(rs.getDouble("x_coordinate"));

						wifi.setY_coordinate(rs.getDouble("y_coordinate"));
						wifi.setWorking_date(rs.getDate("working_date"));

						list.add(wifi);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (historyPreparedStatement != null && !historyPreparedStatement.isClosed()) {
					historyPreparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<History> hitoryDb() {
		ArrayList<History> list = new ArrayList<>();

		String dbUrl = "jdbc:mariadb://localhost:3306/wifidb";
		String dbUserId = "wifiuser";
		String dbPassword = "zerobase";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException var26) {
			throw new RuntimeException(var26);
		}

		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);
			
			statement = connection.createStatement();
			String sql = " SELECT * FROM HISTORY ORDER BY check_date desc; ";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				History history = new History();
				history.setX_coordinate(rs.getDouble("x_coordinate"));
				history.setY_coordinate(rs.getDouble("y_coordinate"));
				history.setDatetime(rs.getTimestamp("check_date"));
				list.add(history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	public void historyDelete(Date date) {
		String dbUrl = "jdbc:mariadb://localhost:3306/wifidb";
		String dbUserId = "wifiuser";
		String dbPassword = "zerobase";
		System.out.println("실행");
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException var26) {
			throw new RuntimeException(var26);
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);
			
			String sql = " delete from HISTORY\r\n"
					+ "where check_date = ?; ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			java.sql.Timestamp ts = new java.sql.Timestamp(date.getTime());
			preparedStatement.setTimestamp(1, ts);
			
			int affected = preparedStatement.executeUpdate();
			if(affected > 0) {
				System.out.println("삭제");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}