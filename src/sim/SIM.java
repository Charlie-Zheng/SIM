package sim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * 
 */

/**
 * @author charl
 * @date Mar 18, 2021
 */
public class SIM {

	public final static int baseHP = 0, HP = 1, baseATK = 2, fATK = 3, pATK = 4, pHP = 5, EM = 6, DMG = 7, CR = 8,
			CD = 9, ER = 10, anemoDMG = 11, geoDMG = 12, electroDMG = 13, hydroDMG = 14, pyroDMG = 15, cryoDMG = 16,
			skillDMG = 17, burstDMG = 18, skillCrit = 19, burstCrit = 20, multReactionDMG = 21, transReactionDMG = 22,
			eRes = 23;

	//damage type tags
	public final static int phys = 0, anemo = 1, geo = 2, electro = 3, hydro = 4, pyro = 5, cryo = 6, NA = 7, CA = 8,
			PA = 9, skill = 10, burst = 11;

	public final static int numTypeStats = 24;
	public final static int numTags = 12;
	private final static String[] statToString = { "BaseHP", "FlatHP", "BaseAtk", "FlatAtk", "Atk%", "HP%", "EM",
			"DMG%", "CR", "CD", "ER", "anemoDMG", "geoDMG", "electroDMG", "hydroDMG", "pyroDMG", "cryoDMG", "skilDMG",
			"burstDMG", "skillCrit", "burstCrit", "MultReactionDMG", "TransReactionDMG", "Enemy Res" };

	public final static int SUBS_PER_NON_STAT_MAIN = 3;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		mainStats[HP] = 4780;
		mainStats[fATK] = 311;
		mainStats[pATK] = 0.466;
		mainStats[ER] = 0.518;
		mainStats[pHP] = 0.466;
		mainStats[EM] = 187;
		mainStats[DMG] = 0.466;
		mainStats[CR] = 0.311;
		mainStats[CD] = 0.622;

		subStats[pHP] = 0.04955;
		subStats[pATK] = 0.04955;
		subStats[fATK] = 19.675;
		subStats[CR] = 0.03305;
		subStats[CD] = 0.06605;
		subStats[EM] = 19.815;
		subStats[ER] = 0.05505;

		double startTime = System.currentTimeMillis();
		//				System.out.println("subs invested\ttotal damage");
		//		for (int i = 25; i <= 40; i += 5) {
		//			talentDamage(i, new int[] { pATK }, new int[] { CR, CD }, new int[] { DMG });
		//		}
		//		System.out.println("-------------------");
		//		System.out.println("subs invested\ttotal damage");

		ArrayList<Integer> subsToCalc = new ArrayList<Integer>();
		for (int i = 0; i <= 25; i += 1) {
			subsToCalc.add(i);
		}
		WeaponDatabase.initSpears();
		for (Weapon spear : WeaponDatabase.spears) {
			data.put(spear.name, new double[46]);
		}

		subsToCalc.parallelStream().forEach(i -> {
			talentDamage(i, new int[] { pATK, ER, EM }, new int[] { CR, CD }, new int[] { DMG }, 1.80f);
		});
		System.out.print("Total subs,");
		for (int subCount : subsToCalc) {
			System.out.print(subCount + ",");
		}
		System.out.println();
		String[] keys = data.keySet().toArray(new String[] {});
		Arrays.sort(keys);
		for (String key : keys) {
			double[] damage = data.get(key);
			System.out.print(key.replace('\t', ' ').replace("  ", " ").replace("\n", "") + ",");
			for (int subCount : subsToCalc) {

				System.out.print((damage[subCount] != 0 ? damage[subCount] : "") + ",");
			}
			System.out.println();
		}
		System.out.println("Time spent: " + (System.currentTimeMillis() - startTime) + " ms");
	}

	static Hashtable<String, double[]> data = new Hashtable<String, double[]>();

	static double[] subStats = new double[numTypeStats];

	static double[] mainStats = new double[numTypeStats];

	public static void talentDamage(int totalSubs, int[] sandStats, int[] circletStats, int[] gobletStats,
			float ERNeeded) {

		//		====================== XIANGLING ======================
				double level = 90;
				float[] charBase = new float[numTypeStats];
				charBase[baseATK] = 225;
				charBase[baseHP] = 10875;
				charBase[CR] = 0.05f;
				charBase[CD] = 0.50f;
				charBase[ER] = 1f;
				charBase[EM] = 96f;
				charBase[eRes] = 0.1f;
				CustomStatMod charMod = null;
		//		====================== Raiden ======================
//		double level = 90;
//		float[] charBase = new float[numTypeStats];
//		charBase[baseATK] = 337;
//		charBase[baseHP] = 12907;
//		charBase[CR] = 0.05f;
//		charBase[CD] = 0.50f;
//		charBase[ER] = 1.32f;
//		charBase[eRes] = 0.1f;
//		CustomStatMod charMod = (stat) -> {
//			stat[electroDMG] += (stat[ER] - 1) * 0.4f;
//		};

		Combo combo = new Combo();
		combo.XLCombo();

		Integer[] subs = { pATK, ER, CR, CD, EM };
		int[] requiredSubs = new int[numTypeStats];
//		requiredSubs[pATK] = 4;
//		requiredSubs[EM] = 4;
		int flower = HP;
		int feather = fATK;

		int counter = 0;

		//		WeaponDatabase.initClaymores();
		StringBuffer output = new StringBuffer();
		for (Weapon spears : WeaponDatabase.spears) {
			float[] startingStats = Arrays.copyOf(charBase, numTypeStats);
			for (int i = 0; i < numTypeStats; i++) {
				startingStats[i] += spears.stats[i];
			}

			double maxDamage = 0;
			int[] maxMains = new int[numTypeStats];
			int[] maxNumSubs = new int[numTypeStats];
			float[] maxStats = new float[numTypeStats];
			double maxAtk = 0;
			double maxHP = 0;
			double talentDMG = 0;
			double reactionDMG = 0;

			for (int sands : sandStats) {
				for (int circlet : circletStats) {
					for (int goblet : gobletStats) {
						int junkStats = 45 - totalSubs;
						int[] maxSubs = new int[numTypeStats];
						for (int stat = 0; stat < numTypeStats; stat++) {
							maxSubs[stat] = SUBS_PER_NON_STAT_MAIN * 5;
						}
						int[] minSubs = new int[numTypeStats];
						for (int mainStat : new int[] { flower, feather, sands, circlet, goblet }) {
							if (maxSubs[mainStat] > 0) {
								maxSubs[mainStat] -= SUBS_PER_NON_STAT_MAIN;
								for (int stat = 0; stat < numTypeStats; stat++) {
									if (stat != mainStat) {
										minSubs[stat]++;
									}
								}
							}
						}

						float[] afterMainStats = Arrays.copyOf(startingStats, numTypeStats);
						afterMainStats[flower] += mainStats[flower];
						afterMainStats[feather] += mainStats[feather];
						afterMainStats[sands] += mainStats[sands];
						afterMainStats[circlet] += mainStats[circlet];
						afterMainStats[goblet] += mainStats[goblet];

						//						pre alloc
						int[] prealloc = new int[numTypeStats];
						for (int s = 0; s < numTypeStats; s++) {
							if (subStats[s] > 0.000001) {
								prealloc[s] += 2;
							}

						}

						int ERSubs = Math.max(0,
								(int) Math.ceil((ERNeeded - afterMainStats[ER]) / subStats[ER]) - prealloc[ER]);
						int totalOffensiveSubs = totalSubs - ERSubs;
						ArrayList<int[]> perms = Permutator.permutate(new LinkedList<Integer>(Arrays.asList(subs)),
								totalOffensiveSubs, maxSubs, requiredSubs);
						for (int[] subsGenerated : perms) {
							int[] numSubs = Arrays.copyOf(subsGenerated, numTypeStats);
							counter += numSubs.length;
							numSubs[ER] += ERSubs;
							for (int s = 0; s < numTypeStats; s++) {
								numSubs[s] += prealloc[s];
							}
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
								totalJunkNeeded += numSubs[i] < minSubs[i] ? minSubs[i] - numSubs[i] : 0;
							}

							if (junkStats - totalJunkNeeded < 0) {
								continue;
							}

							float[] stat = Arrays.copyOf(afterMainStats, numTypeStats);
							for (int i = 0; i < numTypeStats; i++) {
								stat[i] += numSubs[i] * subStats[i];
							}

							spears.mod(stat);
							if (charMod != null) {
								charMod.modStat(stat);
							}
							double totalDamage = combo.calcDamage(stat); //for enemy of same level, def halves the talent damage
							if (totalDamage > maxDamage) {
								//												talentDMG = damage;
								//												reactionDMG = transReactDMG;
								//								System.out.println("er: "  + numSubs[ER] + " * " + subStats[ER] + " + " + afterMainStats[ER]);
								maxDamage = totalDamage;
								maxStats = Arrays.copyOf(stat, stat.length);
								maxMains = new int[] { flower, feather, sands, circlet, goblet };
								maxAtk = stat[baseATK] * (1 + stat[pATK]) + stat[fATK];
								maxHP = stat[baseHP] * (1 + stat[pHP]) + stat[HP];
								maxNumSubs = Arrays.copyOf(numSubs, numSubs.length);
								reactionDMG = combo.calcTransDamage(stat);
								//												System.out.println(Arrays.toString(numSubs));
								//												System.out.println(numSubs[EM]-minSubs[EM]);
							}
						}

					}

				}
			}
			System.out.println(spears.name);
			System.out.println("Total liquid subs:\t" + totalSubs);
			System.out.print("Total Damage:\t" + maxDamage);
			System.out.print("\tTalent damage:\t" + (maxDamage - reactionDMG));
			System.out.println("\tReaction damage:\t" + reactionDMG);
			printMains(maxMains);
			printSubStats(maxNumSubs);
			System.out.println("Total Atk: " + maxAtk);
			System.out.println("Total CR: " + maxStats[CR] * 100);
			System.out.println("Total CD: " + maxStats[CD] * 100);
			System.out.println("Total EM: " + maxStats[EM]);
			System.out.println("Total ER: " + maxStats[ER] * 100);
			System.out.println("Total HP: " + maxHP);
			System.out.println("Configs checked: " + counter);
			System.out.println("----------------------------------------");
			for (Hit h : combo.hits) {
				System.out.println("\t" + h.calcDamage(maxStats) / h.times);
			}
			System.out.println("----------------------------------------");
			data.get(spears.name)[totalSubs] = maxDamage;

		}

	}

	public static void printStats(float[] stat) {
		for (int i = 0; i < numTypeStats; i++) {
			System.out.print(statToString[i] + ": " + stat[i] + ", ");
		}
		System.out.println();
	}

	public static void printSubStats(int[] stats) {
		for (int i = 0; i < numTypeStats; i++) {
			if (stats[i] > 0) {
				System.out.print(statToString[i] + ": " + stats[i] + " = " + stats[i] * subStats[i] + ", ");
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
