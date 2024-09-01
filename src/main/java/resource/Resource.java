package resource;

import lombok.SneakyThrows;
import utilities.ImageRender;

import javax.imageio.ImageIO;
import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Objects;
import java.util.Random;


public class Resource {

	private Resource() {}

	private static final char separatorChar = '/';

	private static final String PATH_MIPMAP = separatorChar + "mipmap" + separatorChar;
	private static final String PATH_FONT = separatorChar + "font" + separatorChar;

	private static final String PATH_ICON_PACK = PATH_MIPMAP + "icon" + separatorChar;
	private static final String PATH_MESH_PACK = PATH_MIPMAP + "mesh" + separatorChar;
	private static final String PATH_SLIDE_PACK = PATH_MIPMAP + "slide" + separatorChar;

	private static final String FONT_PATH_INTER_REGULAR = PATH_FONT + "regular.ttf" ;
	private static final String FONT_PATH_INTER_MEDIUM = PATH_FONT + "medium.ttf" ;
	private static final String FONT_PATH_INTER_SEMIBOLD = PATH_FONT + "semibold.ttf" ;


	public static final String ICON_PATH_AVATAR = PATH_ICON_PACK + "avatar.png";
	public static final String ICON_PATH_ICON = PATH_ICON_PACK + "icon.png";

	public static final String ICON_PATH_LOCK = PATH_ICON_PACK + "lock.png";
	public static final String ICON_PATH_UNLOCK = PATH_ICON_PACK + "unlock.png";
	public static final String ICON_PATH_NEXT_B = PATH_ICON_PACK + "next.png";
	public static final String ICON_PATH_NEXT_R = PATH_ICON_PACK + "next_red.png";
	public static final String ICON_PATH_BACK = PATH_ICON_PACK + "back.png";

	public static final String ICON_PATH_ADD_USER = PATH_ICON_PACK + "add_user.png";
	public static final String ICON_PATH_UPDATE_USER = PATH_ICON_PACK + "update_user.png";
	public static final String ICON_PATH_DELETE_USER = PATH_ICON_PACK + "delete_user.png";
	public static final String ICON_PATH_VIEW_USER = PATH_ICON_PACK + "view_user.png";

	public static final String ICON_PATH_DEPOSIT = PATH_ICON_PACK + "deposit.png";
	public static final String ICON_PATH_WITHDRAW = PATH_ICON_PACK + "withdraw.png";


	public static String getMeshPack() {
		return PATH_MESH_PACK + "gradient_" + new Random().nextInt(12) + ".png";
	}

	public static String getSlidePack() {
		return PATH_SLIDE_PACK + new Random().nextInt(12) + ".png";
	}

	public static Font getInterRegular(int size) {
		return getInter(FONT_PATH_INTER_REGULAR, size);
	}

	public static Font getInterMedium(int size) {
		return getInter(FONT_PATH_INTER_MEDIUM, size);
	}

	public static Font getInterSemibold(int size) {
		return getInter(FONT_PATH_INTER_SEMIBOLD, size);
	}

	@SneakyThrows
	private static Font getInter(String path, int size) {
		return Font.createFont(Font.TRUETYPE_FONT, new File(getResourceAsURI(path))).deriveFont(Font.PLAIN, size);
	}

	@SneakyThrows
	public static Image loadImage(String path) {
		return ImageIO.read(getResourceAsStream(path));
	}

	private static InputStream getResourceAsStream(String path) {
		InputStream input = Resource.class.getResourceAsStream(path);
		Objects.requireNonNull(input);
		return input;
	}

	@SneakyThrows
	private static URI getResourceAsURI(String path) {
		URL url = Resource.class.getResource(path);
		Objects.requireNonNull(url);
		return url.toURI();
	}

	public static PanelUI renderImage(String path) {
		return new ImageRender(path);
	}
}
