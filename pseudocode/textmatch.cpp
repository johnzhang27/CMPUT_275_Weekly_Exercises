/* --------------------------------------------------
*  Name: YongQuan Zhang
*  ID: 1515873
*  CMPUT 275, Winter 2020
*  Weekly Exercise #3: Pseudocode to Code
*---------------------------------------------------*/
#include <iostream>
#include <cstring> // for strlen()

using namespace std;
#define max_length 100001
void textMatch(const char *s, const char *t){
	/* An function includes 2 phases and generated from the pseudocode
	in order to find out the index of appearance of char array s in
	char array t then print index out.

	Args: const char *s: a char array may be a substring of t.
		  const char *t: The char array that may contains s.

	returns: None
	*/

	// phase 1;
	int32_t step[max_length];
	step[0] = -1;
	int32_t k = -1;
	int lengthofs = strlen(s);
	for(int i = 1;i < lengthofs; i++){
		while(k>=0 && s[k+1] != s[i]){
			k = step[k];
		}
		if(s[k+1] == s[i]){
			k = k+1;
		}
		step[i] = k;
	}
	// phase 2;
	int32_t m = -1;
	int lengthoft = strlen(t);
	for(int i = 0; i < lengthoft; i++){
		while(m >= 0 && s[m+1] != t[i]){
			m = step[m];
		}
		if(s[m+1] == t[i]){
			m = m+1;
		}
		if(m == lengthofs-1){
			cout << (i+1 - lengthofs) << " ";
			m = step[m];
		}
	}
	cout << endl;
}

int main() {
	char s[max_length];
	cin >> s;
	char t[max_length];
	cin >> t;
	textMatch(s,t);

	return 0;
}
