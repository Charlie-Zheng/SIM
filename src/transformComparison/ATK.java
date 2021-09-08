package transformComparison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import sim.Weapon;
import sim.WeaponDatabase;

/**
 * 
 */

/**
 * @author charl
 * @date Mar 18, 2021
 */
public class ATK {

	public final static int baseHP = 0, HP = 1, baseATK = 2, fATK = 3, pATK = 4, pHP = 5, EM = 6, DMG = 7, CR = 8,
			CD = 9;
	public final static int numTypeStats = 10;
	private final static String[] statToString = { "BaseHP", "FlatHP", "BaseAtk", "FlatAtk", "Atk%", "HP%", "EM",
			"EleDMG%", "CR", "CD" };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double startTime = System.currentTimeMillis();
		//				System.out.println("subs invested\ttotal damage");
		//		for (int i = 25; i <= 40; i += 5) {
		//			talentDamage(i, new int[] { pATK }, new int[] { CR, CD }, new int[] { DMG });
		//		}
		//		System.out.println("-------------------");
		//		System.out.println("subs invested\ttotal damage");
		System.out.print("Total subs, ");
		for (int i = 19; i <= 19; i += 1) {
			System.out.print(i + ",");
			//			talentDamage(i, new int[] { EM }, new int[] { EM }, new int[] { EM });
			talentDamage(i, new int[] { pATK }, new int[] { CR, CD }, new int[] { DMG });
		}

		System.out.println();
		String[] keys = data.keySet().toArray(new String[] {});
		Arrays.sort(keys);
		for (String key : keys) {
			System.out.print(key.replace('\t', ' ').replace("  ", " ").replace("\n", "") + " Crit,");
			for (double i : data.get(key)) {
				System.out.print(i + ",");
			}
			System.out.println();
		}
		System.out.println("Time spent: " + (System.currentTimeMillis() - startTime) + " ms");
	}

	static Hashtable<String, ArrayList<Double>> data = new Hashtable<String, ArrayList<Double>>();

	public static void talentDamage(int totalSubs, int[] sandStats, int[] circletStats, int[] gobletStats) {
		//		double cBaseAtk = 106;
		//		double wBaseAtk = 608;
		//		double bAtkF = 0;
		//		double bAtk = 0;
		//		double bCR = 0;
		//		double bCD = 0.384 + 0.662;
		//		double bDMG = 0.15;
		//		double bEM = 0;
		//		double bReact = 0.15;
		//		double eRes = 0.1;
		//		double cHP = 15552;
		//		double bHPprct = 0.2;
		//		double hpScale = 0.0626 + 0.018;
		//
		//		boolean isHoma = true;
		//		boolean isHutao = true;

		double level = 90;
		double cBaseAtk = 263;
		double cAtkF = 1000; // bennett
		double cpAtk = 0.2;
		double cCR = 0.05;
		double cCD = 0.50;
		double cDMG = 0;
		double cEM = 0;
		double cHP = 9189;
		double cpHP = 0;
		double chpScale = 0;

		double bMultiReact = 0;
		double bTransReact = 0.6;
		double eRes = 0.1;
		boolean isHoma = false;
		boolean isHutao = false;
		boolean multiReact = false;
		double multiReactType = 1.5;

		double anemoMV = 23.472;
		double infusionMV = 5.4144;
		int transReactNum = 18;

		//1.2 swirl
		double transReactType = 1.2;

		double[] mainStats = new double[numTypeStats];
		mainStats[HP] = 4780;
		mainStats[fATK] = 311;
		mainStats[pATK] = 0.466;
		mainStats[pHP] = 0.466;
		mainStats[EM] = 187;
		mainStats[DMG] = 0.466;
		mainStats[CR] = 0.311;
		mainStats[CD] = 0.622;

		int[] subs = { pATK, fATK, CR, CD, EM };
		double[] subStats = new double[numTypeStats];
		subStats[pHP] = 0.058;
		subStats[pATK] = 0.058;
		subStats[fATK] = 23;
		subStats[CR] = 0.039;
		subStats[CD] = 0.078;
		subStats[EM] = 23;

		int flower = HP;
		int feather = fATK;

		int counter = 0;

		//		WeaponDatabase.initClaymores();
		StringBuffer output = new StringBuffer();
		WeaponDatabase.initBows();
		for (Weapon bow : WeaponDatabase.bows) {
			double wBaseAtk = bow.getBaseAttack();
			double bonusAtkF = cAtkF + bow.getFAtk();
			double bonusAtk = cpAtk + bow.getPAtk();
			double bCR = cCR + bow.getCR();
			double bCD = cCD + bow.getCD();
			double bDMG = cDMG + bow.getDMG();
			double bEM = cEM + bow.getEM();
			double bHPprct = cpHP + bow.getPHP();

			double maxDamage = 0;
			int[] maxMains = new int[numTypeStats];
			int[] maxNumSubs = new int[numTypeStats];
			double[] maxStats = new double[numTypeStats];
			double maxAtk = 0;
			double maxHP = 0;
			double talentDMG = 0;
			double reactionDMG = 0;
			for (int sands : sandStats) {
				for (int circlet : circletStats) {
					for (int goblet : gobletStats) {
						int junkStats = 45 - totalSubs;
						int[] maxSubs = new int[numTypeStats];
						for (int stat : subs) {
							maxSubs[stat] = 30;
						}
						int[] minSubs = new int[numTypeStats];
						for (int stat : subs) {
							minSubs[stat] = 0;
						}
						for (int mainStat : new int[] { sands, circlet, goblet }) {
							if (maxSubs[mainStat] > 0) {
								maxSubs[mainStat] -= 6;
								for (int stat : subs) {
									if (stat != mainStat) {
										minSubs[stat]++;
									}
								}
							}
						}
												if (totalSubs > 41)
													maxSubs[EM] = 2;
												else
													maxSubs[EM] = 1;
						int[] numSubs = new int[numTypeStats];

						for (int numStat0 = 0; numStat0 < maxSubs[subs[0]] + 1; numStat0++) {
							int subsLeft0 = totalSubs - numStat0;
							numSubs[subs[0]] = numStat0;
							for (int numStat1 = 0,
									limit1 = Math.min(maxSubs[subs[1]] + 1, subsLeft0); numStat1 < limit1; numStat1++) {
								int subsLeft1 = subsLeft0 - numStat1;
								numSubs[subs[1]] = numStat1;

								for (int numStat2 = 0, limit2 = Math.min(maxSubs[subs[2]] + 1,
										subsLeft1); numStat2 < limit2; numStat2++) {
									int subsLeft2 = subsLeft1 - numStat2;
									numSubs[subs[2]] = numStat2;

									for (int numStat3 = 0, limit3 = Math.min(maxSubs[subs[3]] + 1,
											subsLeft2); numStat3 < limit3; numStat3++) {
										int subsLeft3 = subsLeft2 - numStat3;
										numSubs[subs[3]] = numStat3;
										if (0 <= subsLeft3 && subsLeft3 <= maxSubs[subs[4]]) {
											int numStat4 = subsLeft3;
											numSubs[subs[4]] = numStat4;
											counter++;
											int first = -1, second = -1;
											for (int i : numSubs) {
												if (i > first) {
													second = first;
													first = i;
												} else if (i > second) {
													second = i;
												}
											}
											if (first + second > 35) {
												continue;
											}

											int totalJunkNeeded = 0;
											for (int i : subs) {
												totalJunkNeeded += numSubs[i] < minSubs[i] ? minSubs[i] - numSubs[i]
														: 0;
											}
											if (junkStats - totalJunkNeeded < 0) {
												continue;
											}
											double[] stat = new double[numTypeStats];
											stat[baseHP] = cHP;
											stat[baseATK] = cBaseAtk + wBaseAtk;
											stat[CR] = bCR;
											stat[CD] = bCD;
											stat[fATK] += bonusAtkF;
											stat[pATK] += bonusAtk;
											stat[DMG] += bDMG;
											stat[EM] += bEM;
											stat[pHP] += bHPprct;
											stat[flower] += mainStats[flower];
											stat[feather] += mainStats[feather];
											stat[sands] += mainStats[sands];
											stat[circlet] += mainStats[circlet];
											stat[goblet] += mainStats[goblet];
											for (int i = 0; i < 5; i++) {
												stat[subs[i]] += numSubs[subs[i]] * subStats[subs[i]];
											}
											double reactR = 1;
											if (multiReact) {
												reactR = multiReactEM(multiReactType, stat[EM], bMultiReact);
											}
											double atk = stat[baseATK] * (1 + stat[pATK]) + stat[fATK];
											double damage;
											if (goblet == DMG) {
												damage = damage(anemoMV, infusionMV, atk, reactR, stat[CR], stat[CD],
														stat[DMG]+0.15, stat[DMG] - mainStats[DMG], 0.1, -0.15);
											} else {
												damage = damage(anemoMV, infusionMV, atk, reactR, stat[CR], stat[CD],
														stat[DMG]+0.15, stat[DMG], 0.1, -0.15);
											}

											double transReactDMG = transReactEM_New(level, stat[EM], bTransReact)
													* transReactType * transReactNum * (1 - (-0.15));
											double totalDamage = damage + transReactDMG; //for enemy of same level, def halves the talent damage
											if (totalDamage > maxDamage) {
												talentDMG = damage;
												reactionDMG = transReactDMG;
												maxDamage = totalDamage;
												maxStats = Arrays.copyOf(stat, stat.length);
												maxMains = new int[] { flower, feather, sands, circlet, goblet };
												maxAtk = atk;
												maxHP = stat[baseHP] * (1 + stat[pHP]) + stat[HP];
												maxNumSubs = Arrays.copyOf(numSubs, numSubs.length);
												//												System.out.println(Arrays.toString(numSubs));
												//												System.out.println(numSubs[EM]-minSubs[EM]);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			System.out.println(bow.name);
			System.out.println("Total subs:\t" + totalSubs);
			System.out.print("Total Damage:\t" + maxDamage);
			System.out.print("\tTalent damage:\t" + talentDMG);
			System.out.println("\tReaction damage:\t" + reactionDMG);
			printMains(maxMains);
			printSubStats(maxNumSubs);
			System.out.println("Total Atk: " + maxAtk);
			System.out.println("Total CR: " + maxStats[CR] * 100);
			System.out.println("Total CD: " + maxStats[CD] * 100);
			System.out.println("Total EM: " + maxStats[EM]);
			System.out.println("Total HP: " + maxHP);
			System.out.println("Configs checked: " + counter);
			System.out.println("----------------------------------------");

			if (!data.containsKey(bow.name)) {
				data.put(bow.name, new ArrayList<Double>());
			}
			data.get(bow.name).add(maxDamage);

		}

	}

	public static void printStats(double[] stats) {
		for (int i = 0; i < numTypeStats; i++) {
			System.out.print(statToString[i] + ": " + stats[i] + ", ");
		}
		System.out.println();
	}

	public static void printSubStats(int[] stats) {
		for (int i = 0; i < numTypeStats; i++) {
			if (stats[i] > 0) {
				System.out.print(statToString[i] + ": " + stats[i] + ", ");
			}

		}
		System.out.println();
	}

	public static void printMains(int[] mains) {
		for (int i = 0; i < 5; i++) {
			System.out.print(statToString[mains[i]] + ", ");
		}
		System.out.println();
	}

	public static double multiReactEM(double base, double EM, double flatReact) {
		return base * (1 + 2.78 * EM / (1400 + EM) + flatReact);
	}

	public static double transReactEM(double level, double EM, double flatReact) {
		return (1 + 6.66 * EM / (1400 + EM) + flatReact)
				* (0.0002325 * level * level * level + 0.05547 * level * level - 0.2523 * level + 14.74);
	}

	public static double transReactEM_New(double level, double EM, double flatReact) {
		return (1 + 16 * EM / (2000 + EM) + flatReact)
				* (0.0002325 * level * level * level + 0.05547 * level * level - 0.2523 * level + 14.74) * 1.20;
	}

	public static double damage(double anemoMV, double infusionMV, double atk, double reactR, double cr, double cd,
			double anemoDMG, double infusionDMG, double anemoRes, double infusionRes) {
		return (anemoMV * rawDamage(atk, reactR, cr, cd, anemoDMG) * (1 - anemoRes)
				+ infusionMV * rawDamage(atk, reactR, cr, cd, infusionDMG)) * 0.5;
	}

	public static double rawDamage(double atk, double reactR, double cr, double cd, double dmg) {
		double crit = 1 + Math.min(cr, 1) * cd;
		return atk * crit * (1 + dmg);
	}

}
