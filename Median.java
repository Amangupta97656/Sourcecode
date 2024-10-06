public class MedianOfTwoSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int low = 0, high = m;

        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (m + n + 1) / 2 - partitionX;

            // Edge cases where partition is at the boundaries
            int maxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            // Check if we have found the correct partition
            if (maxX <= minY && maxY <= minX) {
                // If total length is even, return the average of the two middle elements
                if ((m + n) % 2 == 0) {
                    return ((double) Math.max(maxX, maxY) + Math.min(minX, minY)) / 2;
                } else {
                    // If total length is odd, return the max of the left side
                    return (double) Math.max(maxX, maxY);
                }
            } else if (maxX > minY) {
                // Move partitionX to the left
                high = partitionX - 1;
            } else {
                // Move partitionX to the right
                low = partitionX + 1;
            }
        }

        // If the arrays are not sorted correctly
        throw new IllegalArgumentException("Input arrays are not sorted.");
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};

        System.out.println("The median is: " + findMedianSortedArrays(nums1, nums2));
    }
}
