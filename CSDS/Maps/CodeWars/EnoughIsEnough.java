package Maps.CodeWars;

public class EnoughIsEnough {

	public static int[] deleteNth(int[] elements, int maxOccurrences) {
		TreeMap<Integer, Integer> nums = new TreeMap<>();
        ArrayList<Integer> ns = new ArrayList<Integer>();
        for (int e : elements)
        {
        if (nums.containsKey(e))
        {
            int count = nums.get(e);
            if (count < maxOccurrences)
            {
            ns.add(e);
            }
            count++;
            nums.put(e, count);
        }
        else
        {
            if (maxOccurrences != 0)
            {
            ns.add(e);
            }
            nums.put(e, 1);
        }
        }
        int[] ans = new int[ns.size()];
        for (int x = 0; x < ns.size(); x++)
        {
        ans[x] = ns.get(x);
        }
		return ans;
	}

}
