import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns

df = pd.read_csv('speedtest_results.csv')

sns.lineplot(data=df, x='objects_number', y='sequential_time', label='sequential time')
sns.lineplot(data=df, x='objects_number', y='parallel_time', label='parallel time')

plt.legend(title='Pool size: ' + str(int(df.iloc[0]['pool_size'])))

plt.xscale('log')
plt.show()

df['speedup'] = df['sequential_time'] / df['parallel_time']
sns.lineplot(data=df, x='objects_number', y='speedup')
plt.xscale('log')
plt.show()

df['efficiency'] = df['speedup'] / df['pool_size']
sns.lineplot(data=df, x='objects_number', y='efficiency')
plt.xscale('log')
plt.show()