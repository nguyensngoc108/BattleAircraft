# 👨🏼‍🚀SpaceAircraft🚀

<p align="center"> 
   <img src ="assets/Logo.png"/>
</p>

## 📋 Table of content
* [About](#About)
* [Engines](#Engines)
* [Architecture](#Architecture)
* [Data Structure and Algoprithm](#data-structure-and-algorithm)

---
## About

### 📌 General Information:
- __Game name:__ **Space Aircraft**

- __Category:__ **2D, Action, Shooting, Single player**

-  __Lore:__
      _As the Earth becomes hotter, drought and disease ravage the population,
       Scientists look for a new planet - one that can support human life.
       In 2099, they found it. You and your teammates are sent by the government
       to explore this planet, which is protected by 3 guardians 🛸. The appearance 
       of humans made them worried about the safety of the planet, so they created 
       meteor storms to attack you. Your teammates have sacrificed and you are the
       Earth's only hope right now. Don't let them down._

### 🎮 Rule:
- The rule is simple, dodge the asteroids and shoot the guardian at the same time.
The key is how you show your flexibility in this game.

### 👨‍👦‍👦 Us:
- Team members:
  - Leader: Dương Nguyễn Gia Khánh - ITDSIU20100
  
| No  | Name                           | ID           | Main Contributes                                                                                     | Contacts                                                                                                                                |
|-----|--------------------------------|--------------|------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| 1   | **Dương Nguyễn Gia Khánh** 👨‍💻 | ITDSIU20100  | Game Developer, Game artist, Debug & fixing code, Idea contributor Tasks distributor, Tasks Tracker. | [Github](https://github.com/GiaKhanhs) [Facebook](https://www.facebook.com/profile.php?id=100010473340237)|
| 2   | **Nguyễn Sỹ Nguyên Ngọc** 🎅💻 | ITDSIU20091  | Game Developer, Idea contributor, Debug & fixing code, Quality Checker, Tasks distributor.           | [Github](https://github.com/nguyengoc16) [Facebook](https://www.facebook.com/nguyengoc61)|
| 3   | **Đoàn Hữu Nguyên** 👨🏻‍💻    | ITITIU20260  | Github repository host, Game Developer, Debug & fixing code, Game Sounds & Music.                    | [Github](https://github.com/nguyensngoc108) [Facebook](https://www.facebook.com/NeyAndUgn)|
| 4   | **Nguyễn Trung Kiên** 🧑🏻‍🎨   | ITDSIU20067  | Game designer, Game artist, Slide, Thoughts & Ideas Gatherer, Idea contributor.                      | [Github](https://github.com/K13Z) [Facebook](https://www.facebook.com/nguyenkienhadong)|


## Engines:
- Java Language
- [Oracle JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows) ![Intellij ver](https://img.shields.io/badge/version-2021.3.3-ff69b4).
- [libGDX](https://libgdx.com/) ![libGDX ver](https://img.shields.io/badge/version-1.10.0.-yellowgreen).


## ⚙ Architecture
<p align="center">
<img src= ""/> 
</p>

## Data Structure and Algorithm
- __Data Structure:__
   - Arraylist for store objects: asteroid, effect, bullet.
   - Array for storing animation objects of Player.

- __Algorithm:__
   - ___Boss’s movement:___
	Boss will move base on a particular directions, and that directions will change whenever the boss hit the edge of the screen.
   - ___Bullet Shooting Algorithm:___
   	+ Bullets are created and added to the bullets ArrayList when the condition is satisfied (the spacebar is pressed  and the shootTimer exceeds the SHOOT_TIME).
   	+ The bullets are given initial positions based on the player's position (x and y) and position will be updated over time.
   - ___Asteroid’s spawn:___
      - _Type 1:_ 
      Small: boss’s coordinate, delta time.
      Big: boss’s coordinate, delta time.
      - _Type 2:_ boss’s health < 50%.
	- ___Collision Detection Algorithm:___
		- Collision detection between bullets and asteroids is performed using nested loops that iterate through the bullets and asteroids ArrayLists.
		- The collision is detected using the collidesWith() method of the bullet and asteroid's respective React objects.
		- When a collision occurs, the bullet and asteroid are removed, and an effect is added to the effects ArrayList.

