/*
Gas Station
DescriptionHintsSubmissionsDiscussSolution

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.

Note:

    If there exists a solution, it is guaranteed to be unique.
    Both input arrays are non-empty and have the same length.
    Each element in the input arrays is a non-negative integer.

Example 1:

Input: 
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

Output: 3

Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.

*/
class Solution {
    /*
    Simple math based soln.
    Start at 0
        -if not sufficieant fuel, keep count/sum of fuel needed.
            -since this is not the index from where you can make it, just +1 as possible begin index. At some point it will be correct one !
        -else: keep count of surchanrge fuel
    in the end, if leftover >=0, we can make it.
    */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //cost[i] = fuel needed to go to i+1
        //gas[i] = fuel added
        
        int size=gas.length;
        int remainingGas=0; //remaining gas after one iteration 0-n
        int res=0;
        int nextItrReqdFuel=0; //nextItrReqdFuel fuel
        
        for(int i=0; i<size; ++i){
            remainingGas+=gas[i]-cost[i];
            if(remainingGas<0){
                nextItrReqdFuel += remainingGas;//remainingGas is -ve. imagine burning it in next loop for this move, when we start again from i+1
                remainingGas=0;
                res=i+1;
            }
        }
        int leftover = nextItrReqdFuel + remainingGas;
        
        return leftover<0 ?-1:res;
        
    }
}
