import sys
import os
import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns

path = "output_data/speedtest_pool_results.csv"
output_path = "output_data/speedtest_pool_results_processed.csv"
mode = 0

if len(sys.argv) > 1:
    if not os.path.exists(sys.argv[1]):
        print(f"File '{sys.argv[1]}' doesn't exist.")
    else:
        path = sys.argv[1]

if len(sys.argv) > 2:
    mode = int(sys.argv[2])

print("Mode:", mode)

df = pd.read_csv(path)

def display_plot(y_values, labels, y_log=False, x_log=True):
    for i in range(len(y_values)):
        sns.lineplot(data=df, x='objects_number', y=y_values[i], label=labels[i])

    if x_log: plt.xscale('log')
    if y_log: plt.yscale('log')
    plt.show()

if mode == 0:
    display_plot(['sequential_time', 'parallel_time'], ['sequential', 'parallel'], y_log=True)

    sns.lineplot(data=df, x='objects_number', y='parallel_time', hue='pool_size')
    plt.xscale('log')
    plt.yscale('log')
    plt.show()

    df['speedup'] = df['sequential_time'] / df['parallel_time']
    df['efficiency'] = df['speedup'] / df['pool_size']

    display_plot(['speedup'], [''])
    display_plot(['efficiency'], [''])

    new_df = df[['objects_number', 'sequential_time', 'parallel_time', 'pool_size', 'speedup', 'efficiency']]
    new_df = new_df.reindex(columns=['objects_number', 'sequential_time', 'parallel_time', 'speedup', 'pool_size', 'efficiency'])
    new_df.to_csv(output_path, index=False, float_format='%.2f', sep=';', decimal=',')
else:
    pal = sns.color_palette("tab10")
    sns.lineplot(data=df, x='objects_number', y='sequential_time', hue='threshold', palette=pal)
    plt.xscale('log')
    plt.yscale('log')
    plt.show()

