package tl.lcaptcha;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import tl.lcaptcha.builder.BuilderImpl;
import tl.lcaptcha.listeners.JoinEvent;
import tl.lcaptcha.listeners.MenuClick;
import tl.lcaptcha.listeners.MenuClose;
import tl.lcaptcha.listeners.QuitEvent;
import tl.lcaptcha.utils.RandomUtils;
import tl.lcaptcha.utils.ServerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class Plugin extends JavaPlugin {

    private static Plugin instance;
    private static final List<BuilderImpl> builders = new ArrayList<>();
    private static RandomUtils randomUtils;
    private static ServerUtils serverUtils;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        RegisterEvents();
        randomUtils = new RandomUtils(ThreadLocalRandom.current());
        serverUtils = new ServerUtils(this);
    }

    public void RegisterEvents() {
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MenuClick(), this);
        Bukkit.getPluginManager().registerEvents(new MenuClose(), this);
    }

    public static List<BuilderImpl> getBuilders() {
        return builders;
    }

    public static RandomUtils getRandomUtils() {
        return randomUtils;
    }

    public static Plugin getInstance() {
        return instance;
    }

    public static ServerUtils getServerUtils() {
        return serverUtils;
    }
}
