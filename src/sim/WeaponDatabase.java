package sim;

import java.util.ArrayList;

/**
 * 
 */

/**
 * @author charl
 * @date Mar 18, 2021
 */
public class WeaponDatabase {

	public final static int baseHP = SIM.baseHP, HP = SIM.HP, baseATK = SIM.baseATK, fATK = SIM.fATK, pATK = SIM.pATK,
			pHP = SIM.pHP, EM = SIM.EM, DMG = SIM.DMG, CR = SIM.CR, CD = SIM.CD, ER = SIM.ER, anemoDMG = SIM.anemoDMG,
			geoDMG = SIM.geoDMG, electroDMG = SIM.electroDMG, hydroDMG = SIM.hydroDMG, pyroDMG = SIM.pyroDMG,
			cryoDMG = SIM.cryoDMG, skillDMG = SIM.skillDMG, burstDMG = SIM.burstDMG, skillCrit = SIM.skillCrit,
			burstCrit = SIM.burstCrit, multReactionDMG = SIM.multReactionDMG, transReactionDMG = SIM.transReactionDMG,
			eRes = SIM.eRes;
	public final static int numTypeStats = SIM.numTypeStats;

	public static ArrayList<Weapon> bows = new ArrayList<Weapon>();
	public static ArrayList<Weapon> claymores = new ArrayList<Weapon>();
	public static ArrayList<Weapon> spears = new ArrayList<Weapon>();
	public static ArrayList<Weapon> swords = new ArrayList<Weapon>();
	public static void init() {
		initBows();
	}

	public static void initBows() {
		bows.clear();
		bows.add(new Weapon("Stringless\t\tR1", 510, EM, 165).addDMG(0.4f));
		bows.add(new Weapon("Stringless\t\tR5", 510, EM, 165).addDMG(0.8f));
		bows.add(new Weapon("Prototype Crescent\tR1\tUnactivated", 510, pATK, 0.3f));
		//		bows.add(new Weapon("Prototype Crescent\tR1\tActivated", 510, pATK, 0.3f).addPAtk(0.6f));
		//		bows.add(new Weapon("Prototype Crescent\tR3\tActivated", 510, pATK, 0.3f).addPAtk(0.4f));
		//		bows.add(new Weapon("Prototype Crescent\tR5\tActivated", 510, pATK, 0.3f).addPAtk(0.2f));
		//		bows.add(new Weapon("Alley Hunter\t\tR1\t0/10 Stack", 565, pATK, 0.6f));
		bows.add(new Weapon("Alley Hunter\t\tR1", 565, pATK, 0.6f).addDMG(0.2f));
		bows.add(new Weapon("Alley Hunter\t\tR5", 565, pATK, 0.6f).addDMG(0.4f));
		bows.add(new Weapon("Skyward Harp\t\tR1", 674, CR, 0.1f).addCD(0.2f));
		bows.add(new Weapon("Raven Bow\t\tR5\tActivated", 448, EM, 94).addDMG(0.4f));
		//		bows.add(new Weapon("Raven Bow Base 38\tR5\tActivated", 354, EM, 188).addDMG(0.4f));
		//		bows.add(new Weapon("Old Alley Hunter\tR1\t5/5 Stack", 454, CR, 0.8f).addCD(0.2f).addPAtk(0.2f));
		//		bows.add(new Weapon("Old Alley Hunter\tR5\t5/5 Stack", 454, CR, 0.8f).addCD(0.4f).addPAtk(0.4f));
		//		bows.add(new Weapon("Rust\t\tR5\tActivated", 510, pATK, 0.3f).addDMG(0.8f));
		bows.add(new Weapon("Windblume Ode\t\tR5", 510, EM, 165).addPAtk(0.2f));
		bows.add(new Weapon("Favonius Warbow", 510));
		bows.add(new Weapon("Elegy of the End\tR1\t50% Uptime", 608).addEM(60).addEM(50));
		bows.add(new Weapon("Elegy of the End\tR5\t50% Uptime", 608).addEM(120).addEM(100));
		//		bows.add(new Weapon("Thundering Pulse\t\tR1", 608, CD, 0.2f).addPAtk(0.2f));
		bows.add(new Weapon("Viridescent Hunt\t\tR1 No Passive", 510, CR, 0.6f));
	}

	public static void initClaymores() {
		claymores.clear();
		claymores.add(new Weapon("Rainslasher\t\tR1", 510, EM, 165).addDMG(0.0f));
		claymores.add(new Weapon("Rainslasher\t\tR5", 510, EM, 165).addDMG(0.6f));
		claymores.add(new Weapon("Prototype Archaic\tR1", 565, pATK, 0.6f));
	}

	public static CustomStatMod homaR1 = (stat) -> {
		stat[fATK] += (stat[baseHP] * (1 + stat[pHP]) + stat[HP]) * 0.008;
	};
	public static CustomStatMod homaR5 = (stat) -> {
		stat[fATK] += (stat[baseHP] * (1 + stat[pHP]) + stat[HP]) * 0.016;
	};

	public static CustomStatMod EoSF = (stat) -> {
		stat[burstDMG] += Math.min(stat[ER] * 0.25, 0.75f);
	};
	public static CustomStatMod LawnmowerR1 = (stat) -> {
		stat[pATK] += Math.min((stat[ER] - 1) * 0.28, 0.80);
	};
	public static CustomStatMod LawnmowerR5 = (stat) -> {
		stat[pATK] += Math.min((stat[ER] - 1) * 0.56, 1.20);
	};
	public static CustomStatMod pjcR1 = (stat) -> {
		stat[fATK] += (stat[baseHP] * (1 + stat[pHP]) + stat[HP]) * 0.012;
	};
	public static void initSpears() {
		spears.clear();
//		spears.add(new Weapon("Dragon's Bane\t\tR5", 454, EM, 221).addDMG(0.36f));
//		spears.add(new Weapon("The Catch\t\tR5", 510, ER, 0.459f).add(burstDMG, 0.32f).add(burstCrit, 0.12f));
		spears.add(new Weapon("Skyward Spine\tR1", 674, ER, 0.368f).addCR(0.08f));
//		spears.add(new Weapon("Death Match\tR1", 454, CR, 0.368f).addPAtk(0.16));
//		spears.add(new Weapon("Homa\t\tR1", 608, Weapon.CD, 0.662f).add(pHP, 0.2).addCustomMod(homaR1));
//		spears.add(new Weapon("Engulfing Storm\tR1", 608, ER, 0.551f).addER(0.3f).addCustomMod(LawnmowerR1));
//		spears.add(new Weapon("PJWS\tR1", 674, CR, 0.221f).addPAtk(0.032 * 3));
//		spears.add(new Weapon("<REDACTED>\tR1", 620, pATK,0.138f).add(burstDMG, 0.372));
//		spears.add(new Weapon("<REDACTED>\tR5", 620, pATK,0.138f).add(burstDMG, 0.372*2));
//		spears.add(new Weapon("Kitain\tR1", 565, EM,110).add(skillDMG, 0.12));
		for (Weapon spear: spears) {
//			spear.name += "\t1 stack 4CWF";
//			spear.add(pyroDMG, 0.15f);
//			spear.add(multReactionDMG, 0.15f);
//			spear.add(transReactionDMG, 0.4f);
			spear.name += "\t4EoSF";
			spear.add(ER, 0.2);
			spear.addCustomMod(EoSF);
		}
		//				spears.add(new Weapon("Dragon's Bane\tR5\t4CW\t1stack", 454, EM, 221).addDMG(0.36f).add(pyroDMG, 0.15 + 0.075)
		//						.add(multReactionDMG, 0.15).add(transReactionDMG, 0.4));
		//		spears.add(new Weapon("Dragon's Bane\tR5\t2NO2CWF", 454, EM, 221).addDMG(0.36f).add(burstDMG, 0.2).add(pyroDMG,
		//				0.15));
		//		spears.add(new Weapon("Dragon's Bane\tR5\t2NO2WT", 454, EM, 221).addDMG(0.36f).add(burstDMG, 0.2).add(EM, 80));
		//		spears.add(new Weapon("Dragon's Bane\tR5\t2CWF2WT", 454, EM, 221).addDMG(0.36f).add(pyroDMG, 0.15).add(EM, 80));
		//		spears.add(new Weapon("Dragon's Bane\tR5\tNo Sets", 454, EM, 221).addDMG(0.36f));
	}
	
	public static void initSword() {
		swords.clear();
		swords.add(new Weapon("PJC R1 4EoSF", 542, CR, 0.441f).addPHP(0.2f).addER(0.2f).addCustomMod(EoSF).addCustomMod(pjcR1));
//		swords.add(new Weapon("PJC R1", 542, CR, 0.441f).addPHP(0.2f).addCustomMod(pjcR1).add(hydroDMG, 0.15f).add(burstDMG, 0.2f));
	}
}
