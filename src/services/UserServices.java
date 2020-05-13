package services;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

public class UserServices {

	private static final Random RANDOM = new SecureRandom();
	private static final Base64.Encoder enc = Base64.getEncoder();
	private static final Base64.Decoder dec = Base64.getDecoder();
	DatabaseConnection dbConnection;

	public UserServices(DatabaseConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public byte[] getNewSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		return salt;
	}

	public String getStringFromBytes(byte[] data) {
		return enc.encodeToString(data);
	}

	public byte[] getBytesFromString(String data) {
		return dec.decode(data);
	}

	public String hashPassword(byte[] salt, String password) {

		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f;
		byte[] hash = null;
		try {
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		}
		return getStringFromBytes(hash);
	}

	public boolean register(String username, String password) {
		// TODO: Task 6
		byte[] byteSalt = getNewSalt();
		String theSalt = getStringFromBytes(byteSalt);
		String hashedPassword = hashPassword(byteSalt, password);
		try {
			CallableStatement registerST = dbConnection.getConnection()
					.prepareCall("{? = call dbo.insert_User(?,?,?)}");
			registerST.registerOutParameter(1, Types.INTEGER);
			registerST.setNString(2, username);
			registerST.setString(3, theSalt);
			registerST.setString(4, hashedPassword);
			registerST.executeUpdate();
			int returnValue = registerST.getInt(1);

			if (returnValue == 0) {
				JOptionPane.showMessageDialog(null, "Your account has been registered.");
				return true;
			}

			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "Username cannot be empty.");
				return false;
			}

			if (returnValue == 2) {
				JOptionPane.showMessageDialog(null, "PasswordSalt cannot be left empty.");
				return false;
			}

			if (returnValue == 3) {
				JOptionPane.showMessageDialog(null, "PasswordHash cannot be left empty.");
				return false;
			}

			if (returnValue == 4) {
				JOptionPane.showMessageDialog(null, "ERROR: Username already exists.");
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean login(String username, String password) {
		// TODO: Task 6
		try {
			PreparedStatement loginST = null;
			loginST = dbConnection.getConnection()
					.prepareStatement("SELECT PasswordSalt, PasswordHash \nFROM [User]\n WHERE Username = ?");
			loginST.setString(1, username);

			ResultSet rs = loginST.executeQuery();

			String dbSalt = "";
			String dbHash = "";

			while (rs.next()) {
				dbSalt = rs.getString(1);
				dbHash = rs.getString(2);
			}

			byte[] dbByteSalt = getBytesFromString(dbSalt);
			String newHashcode = hashPassword(dbByteSalt, password);

			if (newHashcode.equals(dbHash)) {
				return true;
			}

			if (!newHashcode.equals(dbHash)) {
				JOptionPane.showMessageDialog(null, "Login Failed. Try Again");
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
}
