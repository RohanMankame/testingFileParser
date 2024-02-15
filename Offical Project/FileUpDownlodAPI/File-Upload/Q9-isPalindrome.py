class Solution:
    def isPalindrome(self, x: int) -> bool:
        # num to str in array
        Sarr = [*(str(x))]
        L = len(Sarr)
        mid = int(L / 2)

        # keep track of first and last element in array
        Start = 0
        Last = L - 1
        # if only 1 element it is already true
        if L == 1:
            return True

        if L % 2 == 0:  # even
            for x in Sarr:
                # Checked all elements now only mid 2 are left, return true if same
                if (Sarr[Start] == Sarr[Last]) and ((Start == mid) or (Last == mid)):
                    return True
                # not equal return false and end
                if (Sarr[Start] != Sarr[Last]):
                    return False
                # check the nect 2 elements one in first half and one from back half
                Start = Start + 1
                Last = Last - 1

        if L % 2 == 1:  # odd
            for x in Sarr:
                # Checked all other elements already, reached the middle value, return true
                if (Start == mid) or (Last == mid):
                    return True
                # False if elements not equal
                if (Sarr[Start] != Sarr[Last]):
                    return False

                Start = Start + 1
                Last = Last - 1 