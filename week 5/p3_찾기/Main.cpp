/*
Original Code :
https://junkwon-dev.github.io/ps/boj-1786/
**/

#include <iostream>
#include<bits/stdc++.h>
using namespace std;
char office[9][9];
int n, m;
int i_index[4] = { -1,0,1,0 };
int j_index[4] = { 0, 1, 0, -1 };

vector<int> _fail(string p) {
	int m = p.size();
	vector<int> fail(m);
	fail[0] = 0;
	int j = 0;
	for (int i = 1; i < m; i++) {
		while (j > 0 && p[i] != p[j])
			j=fail[j - 1];
		if (p[i] == p[j])
			fail[i] = ++j;
		else
			fail[i] = 0;
	}
	return fail;
}



int main(){
	string t;
	string p;
	getline(cin, t);
	getline(cin, p);
	vector<int> fail;
	fail = _fail(p);
	int j = 0;
	int m = t.size();
	vector<int> answer(m);
	for (int i = 0; i < m; i++) {
		while (j > 0 && t[i] != p[j])
		{
			j = fail[j - 1];
		}
		if (t[i] == p[j])
			answer[i] = ++j;
		else
			answer[i] = 0;
	}
	int cnt = 0;
	for (int i = 0; i < m; i++)
	{
		if (answer[i] == p.size()) {
			cnt++;
		}
	}
	cout << cnt<<'\n';
	for (int i = 0; i < m; i++)
	{
		if (answer[i] == p.size())
			cout << i-p.size()+2 << ' ';
	}

}