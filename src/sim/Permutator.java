/**
 * 
 */
package sim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author charl
 * @date Sep. 6, 2021
 */
public class Permutator {

	public final static int baseHP = SIM.baseHP, HP = SIM.HP, baseATK = SIM.baseATK, fATK = SIM.fATK, pATK = SIM.pATK,
			pHP = SIM.pHP, EM = SIM.EM, DMG = SIM.DMG, CR = SIM.CR, CD = SIM.CD, ER = SIM.ER, anemoDMG = SIM.anemoDMG,
			geoDMG = SIM.geoDMG, electroDMG = SIM.electroDMG, hydroDMG = SIM.hydroDMG, pyroDMG = SIM.pyroDMG,
			cryoDMG = SIM.cryoDMG, skillDMG = SIM.skillDMG, burstDMG = SIM.burstDMG, skillCrit = SIM.skillCrit,
			burstCrit = SIM.burstCrit, multReactionDMG = SIM.multReactionDMG, transReactionDMG = SIM.transReactionDMG,
			eRes = SIM.eRes;
	public final static int numTypeStats = SIM.numTypeStats;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Queue<Integer> subs = new LinkedList<Integer>(Arrays.asList(fATK, pATK, EM, CR, CD, ER));
		int[] maxSubs = new int[numTypeStats];
		for (int i = 0; i < numTypeStats; i++) {
			maxSubs[i] = 24;
		}
		int[] minSubs = new int[numTypeStats];
		ArrayList<int[]> perms = permutate(subs, 45, maxSubs, minSubs);
		//		for(int[] p : perms) {
		//			SIM.printSubStats(p);
		//		}
		System.out.println("total: " + perms.size());
		//		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		//		System.out.println(in.next());

	}

	public static ArrayList<int[]>[][] memioize = new ArrayList[numTypeStats][46];

	public static ArrayList<int[]> permutate(Queue<Integer> subs, int totalSubs, int[] maxSubs, int[] minSubs) {

		ArrayList<int[]> perms = new ArrayList<int[]>();
		if (totalSubs < 0) {
			return perms;
			//			throw new UncheckedIOException("Cannot have totalSubs be negative:" + totalSubs, new IOException());
		}
		int stat = subs.poll();
		if (!subs.isEmpty()) {
			for (int i = minSubs[stat]; i <= Math.min(maxSubs[stat], totalSubs); i++) {
				int subsLeft = totalSubs - i;
				ArrayList<int[]> inner;
				if (memioize[subs.peek()][subsLeft] != null) {
					inner = memioize[subs.peek()][subsLeft];
				} else {
					inner = permutate(new LinkedList<Integer>(subs), subsLeft, maxSubs, minSubs);
				}
				for (int[] perm : inner) {
					int[] p = Arrays.copyOf(perm, numTypeStats);
					p[stat] = i;
					perms.add(p);
				}
			}
		} else {
			if (minSubs[stat] <= totalSubs && totalSubs <= maxSubs[stat]) {
				int[] numSubs = new int[numTypeStats];
				numSubs[stat] = totalSubs;
				perms.add(numSubs);

			}
		}
		memioize[stat][totalSubs] = perms;
		return perms;
	}
}
