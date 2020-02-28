/* --------------------------------------------------
*  Name: YongQuan Zhang
*  ID: 1515873
*  CMPUT 275, Winter 2020
*  Weekly Exercise #3: Pseudocode to Code
*---------------------------------------------------*/

#include <iostream>
#include <cmath>

using namespace std;

void flipFlopSort(uint32_t a[], int m, int startlocation){
	int n = m;
	if(n == 2){
		if(a[startlocation] > a[startlocation+1]){
			swap(a[startlocation],a[startlocation+1]);
		}
	}
	else{
		// The reason to use "3.0" is we used to get the up-bounded
		// square root, if use "3" then the ceil funtion will not do
		// anything.
		flipFlopSort(a, ceil(2*n/3.0), startlocation);
		flipFlopSort(a, ceil(2*n/3.0), startlocation + n - (ceil(2*n/3.0)));
		flipFlopSort(a, ceil(2*n/3.0), startlocation);
	}

}

int main(){
	int m;
	cin >> m;
	uint32_t a[200];
	int startlocation = 0;
	for(int i=0; i < m; i++){
		cin >> a[i];
	}
	if(m > 1){
		flipFlopSort(a, m, startlocation);
	}
	for(int j = 0; j < m; j ++){
		cout << a[j] << " ";
	}
	return 0;
}