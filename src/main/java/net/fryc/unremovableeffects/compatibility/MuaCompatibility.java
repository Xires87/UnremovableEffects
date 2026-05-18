package net.fryc.unremovableeffects.compatibility;

import net.fabricmc.loader.api.FabricLoader;
import net.fryc.unremovableeffects.UnremovableEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;

public class MuaCompatibility {

    public static boolean IS_MUA_LOADED = FabricLoader.getInstance().isModLoaded("frycmua");

    public static Class<?> SELECTS_USE_ACTION_INTERFACE = IS_MUA_LOADED ? getClassForName("net.fryc.frycmua.util.mixin_interfaces.player.SelectsUseAction") : null;

    public static Method GET_SELECTED_USE_ACTION_METHOD = SELECTS_USE_ACTION_INTERFACE != null ? getMethodForName(SELECTS_USE_ACTION_INTERFACE, "getSelectedUseAction") : null;

    public static @Nullable Identifier getCurrentlySelectedUseAction(LivingEntity user) {
        if(!IS_MUA_LOADED || SELECTS_USE_ACTION_INTERFACE == null || GET_SELECTED_USE_ACTION_METHOD == null) {
            return null;
        }

        try {
            Object object = GET_SELECTED_USE_ACTION_METHOD.invoke(SELECTS_USE_ACTION_INTERFACE.cast(user));

            return object instanceof Identifier id ? id : null;
        } catch (Exception e) {
            UnremovableEffects.LOGGER.error("MUA compatibility failed despite that all classes were found!");

            return null;
        }
    }

    private static @Nullable Class<?> getClassForName(String name) {
        try {
            return Class.forName(name);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static @Nullable Method getMethodForName(Class<?> clazz, String name, Class<?>... parameters){
        try{
            return clazz.getMethod(name, parameters);
        } catch (Exception e) {
            return null;
        }
    }
}
