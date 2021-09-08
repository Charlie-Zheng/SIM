/**
 * 
 */
package sim;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author charl
 * @date Aug. 18, 2021
 */
public class Hit {
	public final static int baseHP = SIM.baseHP, HP = SIM.HP, baseATK = SIM.baseATK, fATK = SIM.fATK, pATK = SIM.pATK,
			pHP = SIM.pHP, EM = SIM.EM, DMG = SIM.DMG, CR = SIM.CR, CD = SIM.CD, ER = SIM.ER, anemoDMG = SIM.anemoDMG,
			geoDMG = SIM.geoDMG, electroDMG = SIM.electroDMG, hydroDMG = SIM.hydroDMG, pyroDMG = SIM.pyroDMG,
			cryoDMG = SIM.cryoDMG, skillDMG = SIM.skillDMG, burstDMG = SIM.burstDMG, skillCrit = SIM.skillCrit,
			burstCrit = SIM.burstCrit, multReactionDMG = SIM.multReactionDMG, transReactionDMG = SIM.transReactionDMG,
			eRes = SIM.eRes;
	public final static int numTypeStats = SIM.numTypeStats;

	//damage type tags
	public final static int phys = SIM.phys, anemo = SIM.anemo, geo = SIM.geo, electro = SIM.electro, hydro = SIM.hydro,
			pyro = SIM.pyro, cryo = SIM.cryo, NA = SIM.NA, CA = SIM.CA, PA = SIM.PA, skill = SIM.skill,
			burst = SIM.burst;
	public final static int numTags = SIM.numTags;

	float[] buffs = new float[SIM.numTypeStats];
	float MV = 0;
	float reactsMult = 0;
	float reactsTrans = 0;
	boolean[] damageTags = new boolean[SIM.numTags];
	int times = 1;

	/**
	 * @param mV
	 */
	public Hit(float mV) {
		super();
		MV = mV;
	}

	/**
	 * @param mV
	 * @param reactsMult
	 * @param reactsTrans
	 */
	public Hit(float mV, float reactsMult, float reactsTrans) {
		super();
		MV = mV;
		this.reactsMult = reactsMult;
		this.reactsTrans = reactsTrans;
	}

	public Hit setBuff(int stat, float value) {
		buffs[stat] = value;
		return this;
	}
	
	public Hit setBuff(int stat, double value) {
		buffs[stat] = (float) value;
		return this;
	}

	public Hit addTags(int... tags) {
		for (int tag : tags) {
			damageTags[tag] = true;
		}
		return this;
	}

	public Hit setTimes(int times) {
		this.times = times;
		return this;
	}

	public float calcDamage(float[] stat) {
		if (stat.length != numTypeStats) {
			throw new FunctionIOException();
		} else {
			float total = 0;
			stat = Arrays.copyOf(stat, numTypeStats);
			for (int i = 0; i < numTypeStats; i++) {
				stat[i] += buffs[i];
			}
			if (damageTags[skill]) {
				stat[CR] += stat[skillCrit];
				stat[DMG] += stat[skillDMG];
			}
			if (damageTags[burst]) {
				stat[CR] += stat[burstCrit];
				stat[DMG] += stat[burstDMG];
			}
			if (damageTags[anemo]) {
				stat[DMG] += stat[anemoDMG];
			}
			if (damageTags[geo]) {
				stat[DMG] += stat[geoDMG];
			}
			if (damageTags[electro]) {
				stat[DMG] += stat[electroDMG];
			}
			if (damageTags[hydro]) {
				stat[DMG] += stat[hydroDMG];
			}
			if (damageTags[pyro]) {
				stat[DMG] += stat[pyroDMG];
			}
			if (damageTags[cryo]) {
				stat[DMG] += stat[cryoDMG];
			}

			float raw = rawDamage(stat[baseATK] * (1 + stat[pATK]) + stat[fATK], stat[CR], stat[CD], stat[DMG]);
			if (reactsMult < 0.00001) {
				total += MV * raw * 0.5 * (1 - (stat[eRes] < 0 ? stat[eRes] / 2 : stat[eRes]));
			} else {
				total += MV * raw * 0.5 * (1 - (stat[eRes] < 0 ? stat[eRes] / 2 : stat[eRes])) * reactsMult
						* multiReactEM(stat[EM], stat[multReactionDMG]);
			}
			if (reactsTrans != 0) {
				total += reactsTrans * transReactEM_New(90, stat[EM], stat[transReactionDMG]) * (1 - (stat[eRes] < 0 ? stat[eRes] / 2 : stat[eRes]));
			}

			return total * times;
		}
	}

	public static float rawDamage(float atk, float cr, float cd, float dmg) {
		float crit = 1 + Math.min(cr, 1) * cd;
		return atk * crit * (1 + dmg);
	}

	public static float multiReactEM(float EM, float flatReact) {
		return 1 + 2.78f * EM / (1400 + EM) + flatReact;
	}

	public static float transReactEM_New(int level, float EM, float flatReact) {
		return (1 + 16 * EM / (2000 + EM) + flatReact)
				* (0.00194f * level * level * level - 0.319f * level * level + 30.7f * level - 868);
	}
}
