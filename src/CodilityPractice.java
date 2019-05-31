import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class CodilityPractice {
	public static void main(String[] args) {
		/*
		 * int[] res = cyclicRotation(); for (int i = 0; i < res.length; i++) {
		 * System.out.print(res[i] + " "); }
		 */
		System.out.println(TennisTournament(10, 3));
	}

	public static int solution(int N) {
		int binaryGap = 0;
		boolean found = false;

		for (int j = 0; N > 0; N /= 2) {
			if (N % 2 == 0) {
				j++;
			} else {
				if (j > binaryGap && found == true) {
					binaryGap = j;
				}
				found = true;
				j = 0;
			}
		}
		return binaryGap;
	}

	public static int solution2() {
		int[] A = new int[] { 9, 3, 9, 7, 9, 3, 9 };
		boolean possibleSolution;
		int res = 0;
		for (int i = 0; i < A.length; i++) {
			int iVal = A[i];
			possibleSolution = true;
			for (int j = 0; j < A.length; j++) {
				int jVal = A[j];
				if (iVal == jVal && i != j) {
					possibleSolution = false;
					break;
				}
			}

			if (possibleSolution)
				res = A[i];
		}
		return res;
	}

	public static int distinctElements() {
		int[] A = new int[] { 1, 1, 2, 2, 3, 3 };
		Set<Integer> set = new HashSet<>();

		for (int i = 0; i < A.length; i++) {
			set.add(A[i]);
		}
		return set.size();
	}

	public static int[] cyclicRotation() {
		int[] A = { 3, 8, 9, 7, 6 };
		int counter = 0;
		int K = 3;
		while (counter < K) {
			int temp = A[A.length - 1];
			for (int i = A.length - 1; i > 0; i--) {
				A[i] = A[i - 1];
			}
			A[0] = temp;
			counter++;
		}
		return A;
	}

	/**
	 * https://app.codility.com/programmers/lessons/3-time_complexity/frog_jmp/
	 */
	public static int frongJmp(int X, int Y, int D) {
		return (Y - X) / D + ((Y - X) % D == 0 ? 0 : 1);
	}

	/**
	 * https://app.codility.com/programmers/lessons/3-time_complexity/perm_missing_elem/
	 */
	public static int permMissingElem(int[] A) {
		int previous = 0;
		if (A.length != 0) {
			Arrays.sort(A);
			for (int i : A) {
				if (++previous != i) {
					return previous;
				}
			}
		}
		return ++previous;
	}

	/**
	 * https://app.codility.com/programmers/lessons/4-counting_elements/perm_check/
	 */
	public static int PermCheck(int[] A) {
		int previous = 0;
		if (A.length != 0) {
			Arrays.sort(A);
			for (int i : A) {
				if (++previous != i) {
					return 0;
				}
			}
		}
		return 1;
	}

	/**
	 * https://app.codility.com/programmers/lessons/4-counting_elements/frog_river_one/
	 */
	public static int FrogRiverOne(int X, int[] A) {
		int steps = X;
		boolean[] bitmap = new boolean[steps + 1];
		for (int i = 0; i < A.length; i++) {
			boolean b = !bitmap[A[i]];
			if (b) {
				bitmap[A[i]] = true;
				steps--;
			}
			if (steps == 0)
				return i;
		}
		return -1;
	}

	/**
	 * https://app.codility.com/programmers/lessons/4-counting_elements/missing_integer/
	 */
	public static int MissingInteger(int[] A) {
		HashSet<Integer> seen = new HashSet<Integer>();
		int min = 1;

		for (int i = 0; i < A.length; i++) {
			if (A[i] > 0)
				seen.add(A[i]);
		}

		for (int i = 1; i < Integer.MAX_VALUE; i++) {
			if (!seen.contains(i))
				return i;
		}
		return min;
	}

	/**
	 * https://app.codility.com/programmers/lessons/5-prefix_sums/passing_cars/
	 */
	public static int PassingCars(int[] A) {
		int countOfZeros = 0, count = 0;

		for (int i = 0; i < A.length; i++) {
			if (A[i] == 0)
				countOfZeros++;
			if (A[i] == 1)
				count += countOfZeros;
			if (count > 1000000000)
				return -1;
		}
		return count;
	}

	/**
	 * https://app.codility.com/programmers/lessons/5-prefix_sums/min_avg_two_slice/
	 */
	public static int MinAvgTwoSlice(int[] A) {
		int minAvgIdx = 0;
		double minAvgVal = (A[0] + A[1]) / 2; // At least two elements in A.
		double currAvg;
		for (int i = 0; i < A.length - 2; i++) {
			/**
			 * We check first the two-element slice
			 */
			currAvg = ((double) (A[i] + A[i + 1])) / 2;
			if (currAvg < minAvgVal) {
				minAvgVal = currAvg;
				minAvgIdx = i;
			}
			/**
			 * We check the three-element slice
			 */
			currAvg = ((double) (A[i] + A[i + 1] + A[i + 2])) / 3;
			if (currAvg < minAvgVal) {
				minAvgVal = currAvg;
				minAvgIdx = i;
			}
		}
		/**
		 * Now we have to check the remaining two elements of the array Inside
		 * the for we checked ALL the three-element slices (the last one began
		 * at A.length-3) and all but one two-element slice (the missing one
		 * begins at A.length-2).
		 */
		currAvg = ((double) (A[A.length - 2] + A[A.length - 1])) / 2;
		if (currAvg < minAvgVal) {
			minAvgVal = currAvg;
			minAvgIdx = A.length - 2;
		}
		return minAvgIdx;
	}

	/**
	 * https://app.codility.com/programmers/lessons/5-prefix_sums/count_div/
	 */
	public static int CountDiv(int A, int B, int K) {
		return (B / K) - (A / K) + (A % K == 0 ? 1 : 0);
	}

	public static int MaxProductOfThree(int[] A) {
		Arrays.sort(A);
		int F = 0, L = A.length - 1;
		int s1 = A[F] * A[F + 1] * A[F + 2];
		int s2 = A[F] * A[F + 1] * A[L];
		int s3 = A[F] * A[L - 1] * A[L];
		int s4 = A[L - 2] * A[L - 1] * A[L];
		return Math.max(Math.max(s1, s2), Math.max(s3, s4));
	}

	public static int test(int N) {
		int result;
		if (N == 0 || N == 1)
			return 1;

		result = test(N - 1) * N;
		return result;
	}

	/**
	 * https://app.codility.com/programmers/lessons/14-binary_search_algorithm/min_max_division/
	 */
	public static int MinMaxDivision(int K, int M, int[] A) {
		int min = 0;
		int max = 0;
		for (int i = 0; i < A.length; i++) {
			max += A[i];
			min = Math.max(min, A[i]);
		}

		int result = max;
		while (min <= max) {
			int mid = (min + max) / 2;
			if (divisionSolvable(mid, K - 1, A)) {
				max = mid - 1;
				result = mid;
			} else {
				min = mid + 1;
			}
		}
		return result;
	}

	private static boolean divisionSolvable(int mid, int k, int[] a) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			if (sum > mid) {
				sum = a[i];
				k--;
			}
			if (k < 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * https://app.codility.com/programmers/lessons/6-sorting/triangle/
	 */
	public static int Triangle(int[] A) {
		// write your code in Java SE 8
		Arrays.sort(A);
		if (A.length < 3) {
			return 0;
		}
		for (int i = 0; i < A.length - 2; i++) {
			int aI = A[i];
			int aI2 = A[i + 2];
			int aI1 = A[i + 1];
			if (aI > aI2 - aI1) {
				return 1;
			}
		}
		return 0;
	}

	public static int[] count_semiprimes(int N, int[] P, int[] Q) {

		final int[] primes = primes(N);
		final int[] semiPrimes = new int[N + 1];

		for (int num = 2; num <= N; num++) {
			semiPrimes[num] = semiPrimes[num - 1];
			if (primes[num] != 0) {
				if (primes[num / primes[num]] == 0) {
					semiPrimes[num]++;
				}
			}
		}

		int[] result = new int[P.length];
		for (int index = 0; index < Math.min(P.length, Q.length); index++) {
			result[index] = semiPrimes[Q[index]] - semiPrimes[P[index] - 1];
		}
		return result;
	}

	private static int[] primes(int N) {
		final int[] primes = new int[N + 1];
		for (int num = 2; num * num <= N; num++) {
			if (primes[num] == 0) {
				for (int mul = num * num; mul <= N; mul += num) {
					if (primes[mul] == 0) {
						primes[mul] = num;
					}
				}
			}
		}
		return primes;
	}

	/**
	 * https://app.codility.com/programmers/lessons/92-tasks_from_indeed_prime_2016_college_coders_challenge/tennis_tournament/
	 */
	private static int TennisTournament(int P, int C) {
		return P > C && C * 2 < P ? C : P / 2;
	}
}
