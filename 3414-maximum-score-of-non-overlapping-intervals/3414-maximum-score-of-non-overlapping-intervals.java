import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        List<long[]> augmented = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            augmented.add(new long[]{interval.get(0), interval.get(1), interval.get(2), i});
        }
        
        augmented.sort(Comparator.comparingLong(a -> a[1]));
        
        long[][] maxWeight = new long[n + 1][5];
        List<Integer>[][] chosenIndices = new ArrayList[n + 1][5];
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 4; j++) {
                chosenIndices[i][j] = new ArrayList<>();
            }
        }
        
        int[] lastNonOverlapping = new int[n];
        for (int i = 0; i < n; i++) {
            long l = augmented.get(i)[0];
            int low = 0, high = i - 1, idx = -1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (augmented.get(mid)[1] < l) {
                    idx = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            lastNonOverlapping[i] = idx;
        }
        
        for (int i = 1; i <= n; i++) {
            long[] curr = augmented.get(i - 1);
            long weight = curr[2];
            int origIdx = (int) curr[3];
            int prevIdx = lastNonOverlapping[i - 1];
            
            for (int j = 1; j <= 4; j++) {
                long weightExclude = maxWeight[i - 1][j];
                List<Integer> indicesExclude = chosenIndices[i - 1][j];
                
                long weightInclude = weight;
                List<Integer> indicesInclude = new ArrayList<>();
                indicesInclude.add(origIdx);
                
                if (prevIdx != -1 && j > 1) {
                    weightInclude += maxWeight[prevIdx + 1][j - 1];
                    indicesInclude.addAll(chosenIndices[prevIdx + 1][j - 1]);
                } else if (j > 1) {
                    
                }
                
                if (j > 1 && prevIdx == -1) {
                    
                }

                long bestWeight = weightExclude;
                List<Integer> bestIndices = new ArrayList<>(indicesExclude);
                
                // Valid include evaluation
                boolean canInclude = (j == 1) || (prevIdx != -1);
                long computedIncludeWeight = weight;
                List<Integer> computedIncludeIndices = new ArrayList<>();
                computedIncludeIndices.add(origIdx);
                
                if (j > 1) {
                    if (prevIdx != -1) {
                        computedIncludeWeight += maxWeight[prevIdx + 1][j - 1];
                        computedIncludeIndices.addAll(chosenIndices[prevIdx + 1][j - 1]);
                    } else {
                        canInclude = false; 
                    }
                }
                
                if (canInclude) {
                    Collections.sort(computedIncludeIndices);
                    if (computedIncludeWeight > bestWeight) {
                        bestWeight = computedIncludeWeight;
                        bestIndices = computedIncludeIndices;
                    } else if (computedIncludeWeight == bestWeight) {
                        if (compareLists(computedIncludeIndices, bestIndices) < 0) {
                            bestIndices = computedIncludeIndices;
                        }
                    }
                }
                
                maxWeight[i][j] = bestWeight;
                chosenIndices[i][j] = bestIndices;
            }
        }
        
        // best configuration across all j from 1 to 4
        long globalMaxWeight = -1;
        List<Integer> finalBestIndices = new ArrayList<>();
        
        for (int j = 1; j <= 4; j++) {
            if (maxWeight[n][j] > globalMaxWeight) {
                globalMaxWeight = maxWeight[n][j];
                finalBestIndices = new ArrayList<>(chosenIndices[n][j]);
            } else if (maxWeight[n][j] == globalMaxWeight) {
                if (compareLists(chosenIndices[n][j], finalBestIndices) < 0) {
                    finalBestIndices = new ArrayList<>(chosenIndices[n][j]);
                }
            }
        }
        
        Collections.sort(finalBestIndices);
        int[] result = new int[finalBestIndices.size()];
        for (int i = 0; i < finalBestIndices.size(); i++) {
            result[i] = finalBestIndices.get(i);
        }
        return result;
    }
    
    private int compareLists(List<Integer> a, List<Integer> b) {
        int size = Math.min(a.size(), b.size());
        for (int i = 0; i < size; i++) {
            int cmp = Integer.compare(a.get(i), b.get(i));
            if (cmp != 0) return cmp;
        }
        return Integer.compare(a.size(), b.size());
    }
}