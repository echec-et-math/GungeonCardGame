public class MobTemplate extends Mob { // here, replace MobTemplate by Mob name

    String name = ""; // put Mob name here;

    int HP = 0;
    int maxHP = 0;
    final int initialHP = 0; // Please keep initial values the exact same as above. Important.

    int damage = 0;
    final int initialDamage = 0; // Please keep initial values the exact same as above. Important.

    int speed = 0; // Number of cases of movement for the mob.
    final int initialSpeed = 0; // Please keep initial values the exact same as above. Important.

    int range = 0; // Range of the mob (you need a path of said length, or shorter, to your target)
    final int initialRange = 0; // Please keep initial values the exact same as above. Important.

    int cost = 0; // Deployment cost of the mob.
    final int initialCost = 0; // Please keep initial values the exact same as above. Important.

    boolean boss = false; // Determines whether the mob has to be unique in your deck or not. Can be applied to minibosses, NPCs, or anything that should be unique.

    // SOME NEW TRAITS ARE YET TO COME !

    // FOR NOW, UNIQUE ABILITIES DON'T EXIST YET, THEY ARE TO BE EXPECTED.
}