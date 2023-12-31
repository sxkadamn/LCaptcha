package tl.lcaptcha.builder;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tl.lcaptcha.Plugin;

import java.util.ArrayList;
import java.util.List;

public class BuilderImpl {

    private static final String MENU_NAME = Plugin.getInstance().getConfig().getString("menuName").replace("&", "§");
    private static final int ACCEPT_COLOR = 5;
    private static final int FAILED_COLOR = 14;

    private final Inventory inventory;
    private final String owner;
    private boolean canClose;

    private final ItemStack accept;

    public BuilderImpl(int size, String owner) {
        this.accept = createItem("correctName", "correctLore", ACCEPT_COLOR);
        ItemStack failed = createItem("incorrectName", "incorrectLore", FAILED_COLOR);

        int size1 = size * 9;
        this.owner = owner;
        Player player = Bukkit.getPlayer(owner);
        this.inventory = Bukkit.createInventory(player, size1, MENU_NAME);

        int x = Plugin.getRandomUtils().getRandomInt(0, size1 - 1);
        for (int y = 0; y <= size1 - 1; y++) {
            if (y != x) {
                this.inventory.setItem(x, this.accept);
                this.inventory.setItem(y, failed);
            }
        }
        setCanClose(false);
    }

    public void openInventory(Player player) {
        player.openInventory(inventory);
    }

    public static BuilderImpl getBuilder(String owner) {
        return Plugin.getBuilders().stream()
                .filter(builder -> builder.getOwner().equalsIgnoreCase(owner))
                .findFirst()
                .orElse(null);
    }

    private ItemStack createItem(String displayNameKey, String loreKey, int color) {
        FileConfiguration config = Plugin.getInstance().getConfig();
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) color);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(config.getString(displayNameKey).replace("&", "§"));

        List<String> lore = im.getLore();
        if (lore == null) lore = new ArrayList<>();
        lore.add(config.getString(loreKey).replace("&", "§"));
        im.setLore(lore);

        item.setItemMeta(im);
        return item;
    }


    public String getOwner() {
        return this.owner;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public ItemStack getAcceptItem() {
        return this.accept;
    }


    public boolean isCanClose() {
        return this.canClose;
    }


    public void setCanClose(boolean close) {
        this.canClose = close;
    }
}
