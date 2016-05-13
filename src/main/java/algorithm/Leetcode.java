package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Leetcode {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}
	
	 public class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	 }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Leetcode l=new Leetcode();
		char []c={'a','b'};
		System.out.println(c.toString());
	}
	public void bit(int t){
		byte []rs=new byte[32];
		for (int i = 0; i < 32; i++) {
			rs[31-i]=(byte) (t&1);
			t=t>>1;
		}
		for (int i = 0; i < rs.length; i++) {
			System.out.print(rs[i]);
		}
	}
	public int addDigits(int num) {
		while (num > 9) {
			int sum = 0;
			do {
				sum += num % 10;
				num /= 10;
			} while (num > 0);
			num = sum;
		}
		return num;
	}
	public int maxDepth(TreeNode root) {
		if(root==null){
			return 0;
		}
	    int left=maxDepth(root.left);
	    int right=maxDepth(root.right);
	    return (left>right?left:right)+1;
	}
	public void deleteNode(ListNode node) {
	    ListNode next=node.next;
	    node.val=next.val;
	    node.next=next.next;
	}
	public void moveZeroes(int[] nums) {
		int index=0;
        for (int i = 0; i < nums.length; i++) {
			if(nums[i]!=0) nums[index++]=nums[i];
		}
        for (int i = index; i < nums.length; i++) {
			nums[i]=0;
		}
    }
	public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null)
        	return true;
        if(p!=null&&q!=null){
        	if(p.val==q.val&&isSameTree(p.left, q.left)&&isSameTree(p.right, q.right))
        		return true;
        }
        return false;
    }
	 public TreeNode invertTree(TreeNode root) {
	    if(root==null){
	        return root;
	    }
	    root.left=invertTree(root.left);
	    root.right=invertTree(root.right);
	    TreeNode t=root.left;
	    root.left=root.right;
	    root.right=t;
	    return root;
	 }
	 public boolean isAnagram(String s, String t) {
	      //sort and compare
		 char[] sc=s.toCharArray();
		 char[] tc=t.toCharArray();
		 Arrays.sort(sc);
		 Arrays.sort(tc);
		 if(Arrays.toString(sc).equals(Arrays.toString(tc)))
			return true;
		 else
			 return false;
	 }
	 public boolean containsDuplicate(int[] nums) {
		 Arrays.sort(nums);
		 for (int i = 0; i < nums.length-1; i++) {
			if(nums[i]==nums[i+1]){
				return true;
			}
		 }
		 return false;
	}
	 //excel AA->27
	public int titleToNumber(String s) {
	      char[]c=s.toCharArray();
	      int sum=0;
	      for (int i = c.length-1; i >= 0; i--) {
			int n=c[i]-'A'+1;
			sum+=n*Math.pow(26, c.length-1-i);
		}
	      return sum;
	}
	//after sort,the majority will be in the middle
	public int majorityElement(int[] nums) {
	     Arrays.sort(nums);
	     return nums[nums.length/2];
	}
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==p||root==q){
        	return root;
        }
        if(root==null){
        	return null;
        }
        if((root.val-p.val)*(root.val-q.val)<0){
        	return root;
        }else if(p.val>root.val){
        	return lowestCommonAncestor(root.right,p,q);
        }else{
        	return lowestCommonAncestor(root.left,p,q);
        }
    }
	public int hammingWeight(int n) {
		int c=0;
		for(;n!=0;n=n&(n-1)){
			c++;
		}
		return c;
	}
	public int romanToInt(String s) {
        //transform to int[] and process and add
		char[]c=s.toCharArray();
		int[] n=new int[c.length];
		for (int i=0;i<c.length;i++) {
			switch (c[i]) {
			case 'I':
				n[i]=1;
				break;
			case 'V':
				n[i]=5;
				break;
			case 'X':
				n[i]=10;
				break;
			case 'L':
				n[i]=50;
				break;
			case 'C':
				n[i]=100;
				break;
			case 'D':
				n[i]=500;
				break;
			case 'M':
				n[i]=1000;
				break;
			default:
				return -1;
			}
		}
		int sum=n[n.length-1];
		for (int i = 0; i < n.length-1; i++) {
			if(n[i]<n[i+1]){
				sum-=n[i];
			}else{
				sum+=n[i];
			}
		}
		return sum;
    }
	public ListNode reverseList(ListNode head) {
		if(head==null){
			return null;
		}
	    ListNode p,pre,aft;
	    pre=null;p=head;aft=head.next;
	    while(p!=null){
	    	p.next=pre;
	    	pre=p;
	    	p=aft;
	    	if(aft!=null){
	    		aft=aft.next;
	    	}
	    }
	    return pre;
	}
	//the fast way is to use formula
	public int climbStairs(int n) {
	    if(n==1)
	    	return 1;
	    if(n<=0)
	    	return 0;
//	    return climbStairs(n-1)+climbStairs(n-2); //time limit
		int v1=1;
		int v2=2;
		int result=2;
		for (int i = 3; i <=n; i++) {
			result=v1+v2;
			v1=v2;
			v2=result;
		}
		return result;
	}
	public int climbStairs1(int n) {
	    return fbnq(1,2,n);
	}
	public int fbnq(int a,int b,int n){
		if(n==1)
			return a;
		if(n==2)
			return b;
		if(n<1)
			return 0;
		return fbnq(b,a+b,n-1);
	}
	public ListNode deleteDuplicates(ListNode head) {
		if(head==null)
			return null;
        ListNode p=head;
        ListNode aft=head.next;
        while(p!=null){
        	if(aft==null){
        		p.next=aft;
        		break;
        	}
        	if(p.val==aft.val){
        		aft=aft.next;
        	}else{
        		p.next=aft;
        		p=aft;
        		aft=aft.next;
        	}
        }
        return head;
    }
	public boolean isUgly(int num) {
		if(num==1)
			return true;
		if(num<=0)
			return false;
    	while(num%2==0){
    		num=num>>1;
    	}
    	while(num%3==0){
    		num/=3;
    	}
    	while(num%5==0){
    		num/=5;
    	}
        return num==1;
    }
	public boolean isHappy(int n) {
        List<Integer> list=new ArrayList<>();
        while(n!=1&&!list.contains(n)){
        	list.add(n);
        	int s=0;
        	while(n>0){
        		s+=(n%10)*(n%10);
        		n/=10;
        	}
        	n=s;
        }
        return n==1;
    }
	public boolean isPowerOfTwo(int n) {
		if(n<0)
			return false;
		int c=0;
		for(;n!=0;n=n&(n-1)) c++;
        return c==1;
    }
	public boolean isBalanced(TreeNode root) {
        if(root==null)
        	return true;
        if(Math.abs(depthOfTree(root.left)-depthOfTree(root.right))<=1&&isBalanced(root.left)&&isBalanced(root.right)){
        	return true;
        }else{
        	return false;
        }
    }
	public int depthOfTree(TreeNode root){
		if(root==null)
			return 0;
		int l=depthOfTree(root.left);
		int r=depthOfTree(root.right);
		return (l>r?l:r)+1;
	}
	public boolean isSymmetric(TreeNode root) {
        if(root==null)
        	return true;
        return isMirror(root.left,root.right);
    }

	public boolean isMirror(TreeNode left, TreeNode right) {
		if(left==null&&right==null)
			return true;
		if(left==null||right==null)
			return false;
		return left.val==right.val&&isMirror(left.left,right.right)&&isMirror(left.right,right.left);
	}
	public int removeElement(int[] nums, int val) {
        int index=0;
        for (int i = 0; i < nums.length; i++) {
			if(nums[i]!=val){
				nums[index++]=nums[i];
			}
		}
        return index;
    }
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> result=new ArrayList<>();
		if(root==null)return result;
		Queue<TreeNode> q=new LinkedList<>();
		q.add(root);
		while(q.size()>0){
			int size=q.size();
			List<Integer> list=new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode node=q.poll();
				list.add(node.val);
				if(node.left!=null) q.add(node.left);
				if(node.right!=null) q.add(node.right);
			}
			result.add(0, list);
		}
		return result;
	}
	public int removeDuplicates(int[] nums) {
		int index=0;
        for (int i = 0; i < nums.length; i++) {
			if(nums[i]!=nums[index]){
				nums[++index]=nums[i];
			}
		}
        return index+1;
    }
	//recrusive time limit
	public int rob(int[] nums) {
        return robK(nums,nums.length-1);
    }
	public int robK(int[] nums,int k){
		if(k<0) return 0;
		if(k==0) return nums[k];
		return Math.max(robK(nums,k-2)+nums[k], robK(nums,k-1));
	}
	//iterate
	public int rob1(int[] nums){
		if(nums.length<=0) return 0;
		if(nums.length==1) return nums[0];
		if(nums.length==2) return Math.max(nums[0],nums[1]);
		int k2=nums[0];
		int k1=Math.max(nums[0],nums[1]);
		for (int i = 2; i < nums.length; i++) {
			int t=k1;
			k1=Math.max(k2+nums[i], k1);
			k2=t;
		}
		return k1;
	}
	public int[] plusOne(int[] digits) {
	     for (int i = digits.length-1; i >=0; i--) {
	    	if(i==0){
	    		digits[0]++;break;
	    	}
	    	 if(++digits[i]>=10){
	 			digits[i]-=10;
	 		}else{
	 			break;
	 		}
	     }
	     if(digits[0]>=10){
	    	 int[] t=new int[digits.length+1];
	    	 t[0]=1;t[1]=digits[0]-10;
	    	 for (int i = 1; i < digits.length; i++) {
				t[i+1]=digits[i];
			}
	    	 return t;
	     }else{
	    	 return digits;
	     }
	}
	public void plusOneRe(int []digits,int len){
		if(len<1) return;
		if(++digits[len-1]>10){
			digits[len-1]-=10;
			plusOneRe(digits,len-1);
		}
	}
	//Pascal's Triangle
	public List<List<Integer>> generate(int numRows) {
		if(numRows<1){
			return new ArrayList<>();
		}
		if(numRows==1){
			List<List<Integer>> result=new ArrayList<>(); 
			List<Integer> list=new ArrayList<>();
			list.add(1);
			result.add(list);
			return result;
		}
		List<List<Integer>> l=generate(numRows-1);
		List<Integer> li=l.get(l.size()-1);
		List<Integer> ln=new ArrayList<>();
		ln.add(1);
		for (int i = 0; i < li.size()-1; i++) {
			ln.add(li.get(i)+li.get(i+1));
		}
		ln.add(1);
		l.add(ln);
		return l;
    }
	//counts of 2 and 5
	public int trailingZeroes(int n) {
	    int c2=0;int c5=0;
	    int n2=n;int n5=n;
	    while(n2!=0){
	    	n2/=2;
	    	c2+=n2;
	    }
	    while(n5!=0){
	    	n5/=5;
	    	c5+=n5;
	    }
	    return Math.min(c2, c5);
	}
	public List<Integer> getRow(int rowIndex) {
		Integer[]arr=new Integer[rowIndex+1];
        for (int i = 0; i < arr.length; i++) {
			for (int j = i-1; j >=1; j--) {
				arr[j]=arr[j]+arr[j-1];
			}
			arr[i]=1;
		}
        return Arrays.asList(arr);
    }
	public boolean hasPathSum(TreeNode root, int sum) {
		if(root!=null&&root.left==null&&root.right==null&&root.val==sum)
			return true;
		if(root==null)
			return false;
		return hasPathSum(root.left,sum-root.val)||hasPathSum(root.right, sum-root.val);
    }
	public boolean isPalindrome(int x) {
        String s=String.valueOf(x);
        StringBuffer bf=new StringBuffer();
        for (int i = s.length()-1; i >=0; i--) {
			bf.append(s.charAt(i));
		}
        String af=new String(bf);
        return s.equals(af);
    }
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA=headA,pB=headB;
        int lA=0,lB=0;
        while(pA!=null){
        	lA++;pA=pA.next;
        }
        while(pB!=null){
        	lB++;pB=pB.next;
        }
        pA=headA;pB=headB;
        if(lA>lB){
        	int dif=lA-lB;
        	while(dif!=0){
        		pA=pA.next;
        		dif--;
        	}
        }else{
        	int dif=lB-lA;
        	while(dif!=0){
        		pB=pB.next;
        		dif--;
        	}
        }
        while(pA!=pB){
        	pA=pA.next;
        	pB=pB.next;
        }
        if(pA==null)
        	return null;
        else
        	return pA;
	}
	public int minDepth(TreeNode root) {
        if(root==null) return 0;
        if(root!=null&&root.left==null&&root.right==null) return 1;
        int l=minDepth(root.left);
        int r=minDepth(root.right);
        if(l==0&&r==0)
        	return 0;
        if(l==0) return r+1;
        if(r==0) return l+1;
        return (l<r?l:r)+1;
    }
	public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1,j=n-1,k=m+n-1;
        while(i>=0&&j>=0){
        	nums1[k--]=nums1[i]>nums2[j]?nums1[i--]:nums2[j--];
        }
        while(i==-1&&j>=0) nums1[k--]=nums2[j--];
    }
	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			if(!partialValid(board,0,i,8,i)) return false;
			if(!partialValid(board,i,0,i,8)) return false;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(!partialValid(board,i*3,j*3,i*3+2,j*3+2)) return false;
			}
		}
		return true;
    }
	private boolean partialValid(char[][] board,int sX,int sY,int eX,int eY){
		Set<Character> set=new HashSet<>();
		for (int i = sX; i <= eX; i++) {
			for (int j = sY; j <= eY; j++) {
				if(board[i][j]!='.') if(!set.add(board[i][j])) return false;
			}
		}
		return true;
	}
	public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i-k-1]);
            if(!set.add(nums[i])) return true;
        }
        return false;
	}
    public int lengthOfLastWord(String s) {
        if(s==null||s.length()==0) return 0;
        char[]c=s.toCharArray();
        boolean b=true;
        int i=0;
        while(i<c.length){
        	if(c[i]!=' '){
        		b=false;
        		break;
        	}
        	i++;
        }
        if(b) return 0;
        String []sp=s.split(" ");
        String last=sp[sp.length-1];
        return last.length();
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null) return head;
    	ListNode p=head;
        int l=0;
        while(p!=null){
        	l++;
        	p=p.next;
        }
        int h=l-n-1;
        p=head;
        while(h-->0) p=p.next;
        if(n==l){
        	return head.next;
        }
        if(n==1){
        	p.next=null;
        }else{
        	p.next=p.next.next;
        }
        return head;
    }
    public boolean isValid(String s) {
        Stack<Character> stack=new Stack<>();
        for (int i = 0; i < s.length(); i++) {
        	char c=s.charAt(i);
        	stack.add(c);
		}
        Stack<Character> ano=new Stack<>();
        while(!stack.isEmpty()){
        	char c=stack.pop();
        	if(c==')'||c==']'||c=='}'){
        		ano.add(c);
        	}else{
        		if(ano.isEmpty()) return false;
        		char c1=ano.pop();
        		switch (c) {
        		case '(':
        			if(c1!=')') return false;break;
        		case '[':
        			if(c1!=']') return false;break;
				case '{':
					if(c1!='}') return false;break;
				default:
					return false;
				}
        	}
        }
        return ano.isEmpty();
    }
    public String getHint(String secret, String guess) {
    	int A=0,B=0;
    	int [] s=new int[10];
    	int [] b=new int[10];
    	for (int i = 0; i < 10; i++) {
			s[i]=b[i]=0;
		}
    	for (int i = 0; i < secret.length(); i++) {
			int t=Integer.parseInt(String.valueOf(secret.charAt(i)));	
			s[t]++;
		}
    	for (int i = 0; i < guess.length(); i++) {
    		if(guess.charAt(i)==secret.charAt(i)){
    			A++;
    			int t=Integer.parseInt(String.valueOf(secret.charAt(i)));
    			s[t]--;
    		}
    	}
    	for (int i = 0; i < guess.length(); i++) {
    		if(guess.charAt(i)!=secret.charAt(i))
			{
				int t=Integer.parseInt(String.valueOf(guess.charAt(i)));
				if(s[t]-->0) B++;
			}
		}
//    	for (int i = 0; i < b.length; i++) {
//			if(b[i]>0) B++;
//		}
        return A+"A"+B+"B";
    }
    public boolean wordPattern(String pattern, String str) {
        Map<String, String> map=new HashMap<>();
        String[] strs=str.split(" ");
        if(strs.length!=pattern.length()) return false;
        for (int i = 0; i < strs.length; i++) {
        	String key=String.valueOf(pattern.charAt(i));
        	if(map.containsKey(key)){
        		String value=map.get(key);
        		if(!value.equals(strs[i])) return false;
        	}else{
        		if(map.containsValue(strs[i])) return false;
        		map.put(String.valueOf(pattern.charAt(i)), strs[i]);
        	}
		}
        return true;
    }
    public String countAndSay(int n) {
    	if(n<1) return null;
    	if(n==1) return "1";
    	String pre=countAndSay(n-1);
    	int count=0;
    	StringBuffer sb=new StringBuffer();
    	char c=pre.charAt(0);
    	for (int i = 0; i < pre.length(); i++) {
//    		char c=pre.charAt(i);
    		if(c==pre.charAt(i)){
    			count++;
    		}else{
    			sb.append(count);
    			sb.append(c);
    			count=1;
    			c=pre.charAt(i);
    		}
		}
    	if(count>0){
    		sb.append(count);
    		sb.append(c);
    	}
    	return sb.toString();
    }
    public String longestCommonPrefix(String[] strs) {
    	if(strs==null||strs.length==0) return "";
    	String pre=strs[0];
    	int i=0;
    	while(i<strs.length){
    		while(strs[i].indexOf(pre)!=0){
    			pre=pre.substring(0, pre.length()-1);
    		}
    		i++;
    	}
		return pre;
    }
    public List<String> binaryTreePaths(TreeNode root) {
    	List<String> now=new ArrayList<>();
    	if(root==null) return now;
    	if(root.left==null&&root.right==null){
    		now.add(String.valueOf(root.val));
    		return now;
    	}
    	List<String> left=binaryTreePaths(root.left);
    	List<String> right=binaryTreePaths(root.right);
    	if(left!=null){
    		for (String str : left) {
    			now.add(root.val+"->"+str);
    		}
    	}
    	if(right!=null){
    		for (String str : right) {
    			now.add(root.val+"->"+str);
			}
    	}
		return now;
    }
    public int findDuplicate(int[] nums) {
	      for (int i = 0; i < nums.length; i++) {
			for (int j = i; j < nums.length-1; j++) {
				if(nums[i]==nums[j+1]) return nums[i];
			}
	      }  
	      return -1;
    }
    public boolean increasingTriplet(int[] nums) {
    	int str1=Integer.MAX_VALUE;
    	int str2=Integer.MAX_VALUE;
    	for (int i = 0; i < nums.length; i++) {
			if(nums[i]>str2){
				return true;
			}else if(nums[i]>str1){
				str2=Math.min(nums[i], str2);
			}else{
				str1=nums[i];
			}
		}
    	return false;
    }
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
        	if(i==nums.length-1) return nums[i];
			if(nums[i]==nums[i+1]){
				i++;
			}else{
				return nums[i];
			}
		}
        return -1;
    }
    public int singleNumber2(int[] nums) {
    	Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
        	if(i==nums.length-1) return nums[i];
			if(nums[i]==nums[i+1]&&nums[i]==nums[i+2]){
				i+=2;
			}else{
				return nums[i];
			}
		}
        return -1;
    }
    public int sumNumbers(TreeNode root) {
    	return sum(root,0);
    } 
    public int sum(TreeNode root,int s){
    	if(root==null) return 0;
    	if(root.left==null&&root.right==null){
    		return root.val+s*10;
    	}
    	return sum(root.left,s*10+root.val)+sum(root.right,s*10+root.val);
    }
    public int longestConsecutive(int[] nums) {
    	if(nums.length==0) return 0;
    	Arrays.sort(nums);
    	int max=1,curL=1,curNum=nums[0];
    	for (int i = 1; i < nums.length; i++) {
			if((nums[i]-curNum)==1){
				curL++;
				if(curL>max) max=curL;
			}else{
				curL=1;
			}
			curNum=nums[i];
		}
		return max;
    }
    public int[] singleNumber3(int[] nums) {
        Arrays.sort(nums);
        int[] rs=new int[2];
        int index=0;
        for (int i = 0; i < nums.length; i++) {
			if(i==nums.length-1){
				rs[index++]=nums[i];
				break;
			}
			if(nums[i]==nums[i+1]){
				i++;
			}else{
				rs[index++]=nums[i];
			}
		}
        return rs;
    }
    public int maxProfit(int[] prices) {
		return 0;
    }
    int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
    public int missingNumber(int[] nums) {
    	Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i++) {
			if((nums[i+1]-nums[i])!=1) return nums[i]+1;
		}
        if(nums[nums.length-1]!=nums.length) return nums.length;
        return 0;
    }
    public String reverseVowels(String s) {
    	if(s==null) return null;
    	char[] s1=s.toCharArray();
    	int front=0,tail=s1.length-1;
    	while(front<tail){
    		while(front<tail&&!isVowel(s1[front])) front++;
    		while(front<tail&&!isVowel(s1[tail])) tail--;
			char t=s1[front];
			s1[front]=s1[tail];
			s1[tail]=t;
    	}
		return new String(s1);
    }
    //判断一个字母是否元音
    public boolean isVowel(char c){
    	char[] src={'a','e','i','o','u','A','E','I','O','U'};
    	for (int i = 0; i < src.length; i++) {
			if(src[i]==c) return true;
		}
    	return false;
    }
}
