**Requirements for GildedRose Kata refractoring - **
1. Items have SellIn and Quality values-
      This is already implemented in the Item class, where each item has sellIn and quality.
2. Once the sell-by date has passed, Quality degrades twice as fast-
      This logic is handled in the item-specific updates, 
       For Normal items, Conjured items, and Backstage  after the sell-by date passes, the quality degrades appropriately.
3. The Quality of an item is never negative-
      All the strategies (e.g., NormalItemUpdater, ConjuredItemUpdater) ensure that the quality is never less than 0 by using Math.max(item.quality, 0).
4. Aged Brie actually increases in Quality the older it gets-
      The AgedBrieUpdater ensures that the quality of Aged Brie increases with time and doesn't exceed 50, even after the sell-by date has passed.
5. The Quality of an item is never more than 50-
      The AgedBrieUpdater and BackstagePassUpdater ensure that no item's quality exceeds 50.
6. Sulfuras never decreases in Quality and is not affected by SellIn-
      The SulfurasUpdater ensures that Sulfuras never changes in quality and is not affected by the sell-in value. Its quality remains at 80, and its sell-in does not change.
7. Backstage passes' Quality increases by 2 when there are 10 days or fewer, increases by 3 when there are 5 days or fewer, and drops to 0 after the concert-
      The BackstagePassUpdater correctly handles the logic for Backstage passes
8. Conjured items degrade in Quality twice as fast as normal items-
      The ConjuredItemUpdater degrading the quality twice as fast after the sell-by date.
9. You cannot modify the Item class or items property-
      Item class not modified. Instead,implemented dedicated classes to handle the item-specific behavior.
10. Quality of Sulfuras is always 80-
       The SulfurasUpdater ensures Sulfuras always has a quality of 80 and doesn't change, as per the requirement.
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    For existing implemention(main & test), please refer package com.gildedrose.v1
    For the refactored code(main & test), please refer package com.gildedrose.v2
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Summary of existing and refractored code - 
1. Large and Complex Method
Existing: updateQuality is long and hard to read with lots of conditionals.
Refactored: Logic is moved to individual ItemUpdater classes, making the method simpler.

2. Hard to Extend
Existing: Adding new item types requires modifying the updateQuality method.
Refactored: New item types are added by creating new ItemUpdater classes, making it easy to extend.

3. Code Duplication
Existing: Item-specific logic is repeated in the updateQuality method.
Refactored: Eliminates duplicates, as each item type has its own updater class.

4. Low Maintainability
Existing: Changes to one item’s behavior could break others.
Refactored: Each item’s behavior is isolated in its own class, reducing risk of breaking other parts of the system.

5. Difficulty in Testing
Existing: Testing is difficult due to the complex updateQuality method.
Refactored: Each item scenerios can be tested independently.

6. Risk of Errors
Existing: Modifying conditions in updateQuality increases risk of bugs and high posibility of regression.
Refactored: Changes are confined to specific classes, reducing error risks.

7. Limited Reusability
Existing: Item-specific logic is not easily reusable elsewhere.
Refactored: Item-specific logic is encapsulated in classes, making it reusable.

    
