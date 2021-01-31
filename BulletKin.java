public class BulletKin extends Mob {

    public BulletKin() {
    
        name = "Bullet Kin"; // put Mob name here;

        HP = 2;
        maxHP = 2;
        initialHP = 2; // Please keep initial values the exact same as above. Important.
    
        damage = 1;
        initialDamage = 1; // Please keep initial values the exact same as above. Important.
    
        speed = 1; // Number of cases of movement for the mob.
        initialSpeed = 1; // Please keep initial values the exact same as above. Important.
    
        range = 1; // Range of the mob (you need a path of said length, or shorter, to your target)
        initialRange = 1; // Please keep initial values the exact same as above. Important.
    
        cost = 2; // Deployment cost of the mob.
        initialCost = 2; // Please keep initial values the exact same as above. Important.
    
        boss = false; // Determines whether the mob has to be unique in your deck or not. Can be applied to minibosses, NPCs, or anything that should be unique.
    
        // SOME NEW TRAITS ARE YET TO COME !
    
        // FOR NOW, UNIQUE ABILITIES DON'T EXIST YET, THEY ARE TO BE EXPECTED.
    }

}
