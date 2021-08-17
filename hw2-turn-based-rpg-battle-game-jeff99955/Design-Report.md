# Design Report
> Please follow the instructions in homework 2 (officially announced version on NTU COOL) to finish the report.

## Software Design

> In this design report, you **DO NOT** need to illustrate your design of every class. 
> You only need to write down how do you achieve the Open-Close principle on those requirements which ask you to follow the Open-Close principle under certain cases.

To obtain OCP, I make **Skill** an abstract class with two methods, `consumeMP()` and `perform()`. `consumeMP()` is a common method for all skills that reduces MP from a unit, while `perform()` is a method that requires each specific skills to implement. In this problem, I put the *output to stream (System.out.print*)`* session in `perform()` and provided sample utility functions in **Output**, while one could customize what to print by injecting their own code.

- Follow the Open-Close Principle on removing/adding the skill SelfHealing
    * In `perform()`, simply add up the health to the unit.

- Follow the Open-Close Principle on removing/adding the skill Summon
    * In `perform()`, simply create a unit using constructor and call `addMember()` to add the unit to the back of the troop. In this case, we add the troop to which a unit belongs for the convenience of adding the slime we summoned.

- Follow the Open-Close Principle on removing/adding the skill Petrochemical
    * In `perform()`, set the state to **PetrochemicalState**. To determine whether a unit could attack or not, each **State** has a method called `canCast()`, usually `true`, in **Petrochemical**, return `false`.

## Bonus Design

- Follow the Open-Close Principle on removing/adding the skill Curse
    * We have to memorize the cast relationship, so we memorize the caster by maintaining a set in target **Unit** in `perform()`.

- Follow the Open-Close Principle on removing/adding the skill Cheerup
    * In `perform()`, set the state to Cheerup. To add up the damage for caster, each state has a method **getAdditionalDamage()**, usually returns 0, if in Cheerup state, return 50.

- Follow the Open-Close Principle on removing/adding the skill OnePunch
    * Everything is done in **OnePunch**
    * First, override the `getRequiredMP()` and plug in the provided `getMpCost()`. 
    * Secondly, in `perform()`, obtain the damage first by overriding `takeOnePunchDamage()` in **OnePunch** and creating a class implementing **Target**.
    * The rest is like basic attack.
