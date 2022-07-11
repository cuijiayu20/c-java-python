#include <iostream>
#include <cstring>
#include <algorithm>

using namespace std;

const int N = 10, M = 20, B = M / 2;

int n, m;
int w[N];
bool f[N][M];

int main()
{
    scanf_s("%d", &n);
    for (int i = 1; i <= n; i++) scanf_s("%d", &w[i]), m += w[i];

    f[0][B] = true;
    for (int i = 1; i <= n; i++)
        for (int j = -m; j <= m; j++)
        {
            f[i][j + B] = f[i - 1][j + B];
            if (j - w[i] >= -m) f[i][j + B] |= f[i - 1][j - w[i] + B];
            if (j + w[i] <= m) f[i][j + B] |= f[i - 1][j + w[i] + B];
        }

    int res = 0;
    for (int j = 1; j <= m; j++)
        if (f[n][j + B])
            res++;
    printf("%d\n", res);
    return 0;
}

