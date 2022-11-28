package interfaces

import factories.WeaponFactory

interface DamageDealerInterface : WeaponizableInterface{
    var damages: Int

    fun attack() {
        if (Renderer.hero.weapons.first().canFire()) {
            Renderer.hero.weapons.add(WeaponFactory.createBullet())
        }

        weapons.removeIf {
            it.needRemoval
        }

        weapons.forEach {
            it.attack()
        }
    }
}