/**
 * 
 */
package sim;

import java.util.ArrayList;

/**
 * @author charl
 * @date Aug. 18, 2021
 */
public class Combo {
	ArrayList<Hit> hits = new ArrayList<Hit>();
	ArrayList<Hit> transformatives = new ArrayList<Hit>();
	public final static int baseHP = SIM.baseHP, HP = SIM.HP, baseATK = SIM.baseATK, fATK = SIM.fATK, pATK = SIM.pATK,
			pHP = SIM.pHP, EM = SIM.EM, DMG = SIM.DMG, CR = SIM.CR, CD = SIM.CD, ER = SIM.ER, anemoDMG = SIM.anemoDMG,
			geoDMG = SIM.geoDMG, electroDMG = SIM.electroDMG, hydroDMG = SIM.hydroDMG, pyroDMG = SIM.pyroDMG,
			cryoDMG = SIM.cryoDMG, skillDMG = SIM.skillDMG, burstDMG = SIM.burstDMG, skillCrit = SIM.skillCrit,
			burstCrit = SIM.burstCrit, eRes = SIM.eRes;
	public final static int numTypeStats = SIM.numTypeStats;

	//damage type tags
	public final static int phys = SIM.phys, anemo = SIM.anemo, geo = SIM.geo, electro = SIM.electro, hydro = SIM.hydro,
			pyro = SIM.pyro, cryo = SIM.cryo, NA = SIM.NA, CA = SIM.CA, PA = SIM.PA, skill = SIM.skill,
			burst = SIM.burst;
	public final static int numTags = SIM.numTags;

	public void XLCombo() {
		final float e = 2.003f, q1 = 1.53f, q2 = 1.87f, q3 = 2.329f, qSpin = 2.38f;
		hits.clear();
		//		hits.add(new Hit(e, 1.5f, 0f).addTags(skill, pyro).setTimes(3).setBuff(fATK, 0.000123).setBuff(pATK, 0.93f)
		//				.setBuff(EM, 150)); 
		//		hits.add(new Hit(q1, 1.5f, 0f).addTags(burst, pyro).setBuff(fATK, 0.000123).setBuff(pATK, 0.93f).setBuff(EM, 150));
		//		hits.add(new Hit(q2).addTags(burst, pyro).setBuff(fATK, 0.000123).setBuff(pATK, 0.93f).setBuff(EM, 150));
		//		hits.add(new Hit(q3).addTags(burst, pyro).setBuff(fATK, 0.000123).setBuff(pATK, 0.93f).setBuff(EM, 150));
		//		hits.add(new Hit(qSpin, 1.5f, 0f).addTags(burst, pyro).setTimes(12).setBuff(fATK, 0.000123).setBuff(pATK, 0.93f)
		//				.setBuff(EM, 150));
		hits.add(new Hit(e, 1.5f, 0.0f).addTags(skill, pyro).setTimes(3).setBuff(fATK, 915).setBuff(pATK, 0.93f)
				.setBuff(eRes, -0.55f).setBuff(EM, 150));
		hits.add(new Hit(e, 0.0f, 0.0f).addTags(skill, pyro).setTimes(1).setBuff(fATK, 915).setBuff(pATK, 0.93f)
				.setBuff(eRes, -0.55f).setBuff(EM, 150));

		hits.add(new Hit(q1).addTags(burst, pyro).setBuff(fATK, 915).setBuff(pATK, 0.93f).setBuff(eRes, -0.55f).setBuff(EM, 150));
		hits.add(new Hit(q2).addTags(burst, pyro).setBuff(fATK, 915).setBuff(pATK, 0.93f).setBuff(eRes, -0.55f).setBuff(EM, 150));
		hits.add(new Hit(q3).addTags(burst, pyro).setBuff(fATK, 915).setBuff(pATK, 0.93f).setBuff(eRes, -0.55f).setBuff(EM, 150));
		hits.add(new Hit(qSpin, 1.5f, 0f).addTags(burst, pyro).setTimes(10).setBuff(fATK, 915).setBuff(pATK, 0.93f)
				.setBuff(eRes, -0.55f).setBuff(EM, 150));
		hits.add(new Hit(qSpin, 0f, 0f).addTags(burst, pyro).setTimes(2).setBuff(fATK, 915).setBuff(pATK, 0.93f)
				.setBuff(eRes, -0.55f).setBuff(EM, 150));

		transformatives.clear();
//		transformatives.add(new Hit(0.0f, 0.0f, 4f).addTags(pyro).setBuff(eRes, -0.55f).setTimes(7));
	}

	public void RaidenCombo() {
		// t9
		int resolve = 53;
		float resolve_ratio0 = 0.0661f;
		float resolve_ratio1 = 0.0123f;
		final float e = 1.9924f, e_proc = 0.714f, q = 6.8136f + resolve * resolve_ratio0,
				n5 = .7524f + .7393f + .90520f + (.5195f + .521f) + 1.2436f + resolve * resolve_ratio1 * 6,
				n4 = .7524f + .7393f + .90520f + (.5195f + .521f) + resolve * resolve_ratio1 * 5,
				n1 = .7524f + resolve * resolve_ratio1, ca = 1.036f + 1.2506f + resolve * resolve_ratio1 * 2;
		hits.clear();
		hits.add(new Hit(e).addTags(electro, skill).setBuff(fATK, 915).setBuff(pATK, 0.45));
		hits.add(new Hit(e_proc).addTags(electro, skill).setTimes(7).setBuff(fATK, 915).setBuff(pATK, 0.45));
		hits.add(new Hit(e_proc).addTags(electro, skill).setTimes(7).setBuff(pATK, 0.25));
		hits.add(
				new Hit(q).addTags(electro, burst).setBuff(fATK, 915).setBuff(pATK, 0.45).setBuff(burstDMG, 0.27));
		hits.add(new Hit(n5).addTags(electro, burst).setTimes(3).setBuff(fATK, 915).setBuff(pATK, 0.45)
				.setBuff(burstDMG, 0.27));
//		hits.add(new Hit(n4).addTags(electro, burst).setBuff(fATK, 915).setBuff(pATK, 0.45).setBuff(burstDMG,
//				0.27));
		transformatives.clear();
		transformatives.add(new Hit(0.0f, 0.0f, 4f).addTags(pyro).setTimes(3));
	}

	public float calcDamage(float[] stat) {
		float total = 0;
		for (Hit h : hits) {
			total += h.calcDamage(stat);
		}

		for (Hit h : transformatives) {
			total += h.calcDamage(stat);
		}
		return total;
	}

	public float calcTransDamage(float[] stat) {
		float total = 0;
		for (Hit h : transformatives) {
			total += h.calcDamage(stat);
		}
		return total;
	}
}
