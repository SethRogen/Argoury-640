package com.ziotic.logic.item;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ziotic.Constants;
import com.ziotic.utility.Logging;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class ItemPriceLoader {

	
    /**
     * Logger instance.
     */
    private static final Logger logger = Logging.log();
    
    private final Map<Integer, Integer> prices = new HashMap<>();

    public void readPrices() {
        try (FileReader reader = new FileReader(Constants.WORK_DIR + "/world/items/prices.json")) {
            Gson gson = new Gson();

            // Define the type of list we expect
            Type listType = new TypeToken<List<ItemEntry>>() {}.getType();
            List<ItemEntry> items = gson.fromJson(reader, listType);

            // Convert list to map
            for (ItemEntry entry : items) {
                prices.put(entry.getItemID(), entry.getPrice());
            }
            logger.info("Loaded " + prices.size() + " item prices from JSON.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPrice(int itemId) {
        return prices.getOrDefault(itemId, 0);
    }

    public void printPrices() {
        prices.forEach((id, price) ->
            System.out.println("ItemID: " + id + " | Price: " + price)
        );
    }

    // inner class for each JSON entry
    private static class ItemEntry {
        private int ItemID;
        private int Price;

        public int getItemID() {
            return ItemID;
        }

        public int getPrice() {
            return Price;
        }
    }
}
