import java.util.*;


public class MinimumCostFinder {
    public static void main(String[] args) {
        List<Integer> distances = Arrays.asList(10,10,10,30);
        List<Integer> prices = Arrays.asList(2,3,4,5);
        System.out.println(getMinCost(distances,prices));
    }

    private static int getMinCost(List<Integer> distances, List<Integer> prices) {
        //x1+x2+x3 = g1+g2+g3;
        //min(p1g1+p2g2+p3g3) gi<=50 g1>=x1
        int[] cost = new int[distances.size() +1];
        int[] tank = new int[distances.size() +1];
        int[] extraCost = new int[distances.size() +1];
        for (int i = 1; i <= distances.size(); i++) {
            int dist=0;
            int min =Integer.MAX_VALUE;
            for (int j = i-1; j >=0; j--) {
                dist+= distances.get(j);
                if(dist>50){
                    break;
                }
                if(cost[j]+ prices.get(j) *dist<min){
                    min = cost[j]+ prices.get(j) *dist;
                    tank[i] = 50-dist;
                    extraCost[i] = (50-dist)* prices.get(j);
                }

                if(tank[j]<dist && cost[j]+extraCost[j]+ prices.get(j) *(dist-tank[j])<min){
                    min = cost[j]+extraCost[j]+ prices.get(j) *(dist-tank[j]);
                    tank[i] = 50-dist+tank[j];
                    extraCost[i] = (50-dist+tank[j])* prices.get(j);
                }
            }
            cost[i] = min;
        }
        return cost[distances.size()];
    }
}
