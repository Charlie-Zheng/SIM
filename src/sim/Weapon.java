package sim;

import java.util.ArrayList;

/**
 * 
 */

/**
 * @author charl
 * @date Mar 18, 2021
 */
public class Weapon {
	public final static int baseHP = SIM.baseHP, HP = SIM.HP, baseATK = SIM.baseATK, fATK = SIM.fATK, pATK = SIM.pATK,
			pHP = SIM.pHP, EM = SIM.EM, DMG = SIM.DMG, CR = SIM.CR, CD = SIM.CD, ER = SIM.ER, anemoDMG = SIM.anemoDMG,
			geoDMG = SIM.geoDMG, electroDMG = SIM.electroDMG, hydroDMG = SIM.hydroDMG, pyroDMG = SIM.pyroDMG,
			cryoDMG = SIM.cryoDMG, skillDMG = SIM.skillDMG, burstDMG = SIM.burstDMG, skillCrit = SIM.skillCrit,
			burstCrit = SIM.burstCrit, multReactionDMG = SIM.multReactionDMG, transReactionDMG = SIM.transReactionDMG,
			eRes = SIM.eRes;
	public final static int numTypeStats = SIM.numTypeStats;
	public float[] stats = new float[numTypeStats];
	public String name;

	public ArrayList<CustomStatMod> mods = new ArrayList<CustomStatMod>();

	public Weapon(String name, float BaseAttack) {
		this.name = name;
		stats[baseATK] = BaseAttack;
	}

	public Weapon(String name, float BaseAttack, int secStatType, float secStatVal) {
		this.name = name;
		stats[baseATK] = BaseAttack;
		stats[secStatType] = secStatVal;
	}

	public Weapon addBaseAttack(double value) {
		stats[baseATK] += value;
		return this;
	}

	public Weapon addPAtk(double value) {
		stats[pATK] += value;
		return this;
	}

	public Weapon addPHP(double value) {
		stats[pHP] += value;
		return this;
	}

	public Weapon addEM(double value) {
		stats[EM] += value;
		return this;
	}

	public Weapon addDMG(double value) {
		stats[DMG] += value;
		return this;
	}

	public Weapon addCR(double value) {
		stats[CR] += value;
		return this;
	}

	public Weapon addCD(double value) {
		stats[CD] += value;
		return this;
	}

	public Weapon addER(double value) {
		stats[ER] += value;
		return this;
	}

	public Weapon add(int stat, double value) {
		stats[stat] += value;
		return this;
	}

	public void mod(float[] stat) {
		for(CustomStatMod mod : mods) {
			mod.modStat(stat);
		}
	}
	
	public Weapon addCustomMod(CustomStatMod mod) {
		mods.add(mod);
		return this;
	}

	public double getBaseAttack() {
		return stats[baseATK];
	}

	public double getFAtk() {
		return stats[fATK];
	}

	public double getPAtk() {
		return stats[pATK];
	}

	public double getPHP() {
		return stats[pHP];
	}

	public double getEM() {
		return stats[EM];
	}

	public double getDMG() {
		return stats[DMG];
	}

	public double getCR() {
		return stats[CR];
	}

	public double getCD() {
		return stats[CD];
	}

	public double getER() {
		return stats[ER];
	}

}
