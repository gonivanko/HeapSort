import sys
import os
import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns

path = "data/speedtest_results.csv"
mode = 0

if len(sys.argv) > 1:
    if not os.path.exists(sys.argv[1]):
        print(f"File '{sys.argv[1]}' doesn't exist.")
    else:
        path = sys.argv[1]

if len(sys.argv) > 2:
    mode = int(sys.argv[2])

df = pd.read_csv(path)

def display_plot(y_values, labels, y_log=False, x_log=True):
    for i in range(len(y_values)):
        sns.lineplot(data=df, x='objects_number', y=y_values[i], label=labels[i])

    if x_log: plt.xscale('log')
    if y_log: plt.yscale('log')
    plt.show()

if mode == 0:
    display_plot(['sequential_time', 'parallel_time'], ['sequential', 'parallel'], y_log=True)
    df['speedup'] = df['sequential_time'] / df['parallel_time']
    df['efficiency'] = df['speedup'] / df['pool_size']

    display_plot(['speedup'], ['speedup'])
    display_plot(['efficiency'], ['efficiency'])
else:
    display_plot(['sequential_time'], ['sequential'], True)

