package codyhuh.brokenknuckles.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class BKKeyBindings {
    public static final String KEY_CATEGORY_BK = "key.category.brokenknuckles.bloodandiron";
    public static final String KEY_ACTIVATE_INVIS = "key.brokenknuckles.activate_invis";

    public static final KeyMapping INVIS_KEY = new KeyMapping(KEY_ACTIVATE_INVIS, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_BK);
}
