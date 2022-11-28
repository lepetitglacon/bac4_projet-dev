package factories

import weapons.*

object WeaponFactory {
    fun createSword(): Sword {
        val props = PropertyReader.readFile("sword")
        return Sword(
            props.getProperty("width").toInt(),
            props.getProperty("height").toInt(),
            Renderer.hero.position.clone(),
            Renderer.hero.velocity.clone(),
            props.getProperty("speed").toInt()
        )
    }


    fun createBullet(): Bullet {
        val props = PropertyReader.readFile("bullet")
        val bullet = Bullet(
            props.getProperty("width").toInt(),
            props.getProperty("height").toInt(),
            Renderer.hero.position.clone(),
            Renderer.hero.velocity.clone(),
            props.getProperty("speed").toInt()
        )
        bullet.velocity = Renderer.hero.velocity
        return bullet
    }
}