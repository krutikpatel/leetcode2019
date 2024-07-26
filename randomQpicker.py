import os
import random

def pick_random_file(directory, subdirs, filename_prefix=None):
    files = []
    # Set containing normalized paths of the subdirectories we are interested in
    subdirs_set = {os.path.normpath(os.path.join(directory, subdir)) for subdir in subdirs}
    
    # Walk through all directories and files in the specified directory
    for root, dirs, files_in_dir in os.walk(directory):
        # Normalize the root to handle different path conventions
        normalized_root = os.path.normpath(root)
        
        # Check if the current root is in the subdirectories we are interested in
        if any(normalized_root.startswith(subdir) for subdir in subdirs_set):
            for file in files_in_dir:
                # Add file if filename_prefix is not provided or if file starts with the prefix
                if filename_prefix is None or file.startswith(filename_prefix):
                    filepath = os.path.join(root, file)
                    files.append(filepath)
    
    # Choose a file randomly from the list of collected files
    return random.choice(files) if files else None

# Example usage:
directory_path = '.'
subdirectories = ['binary_tree']  # List of subdirectories to include
filename_prefix = 'm_'  # Files must start with this prefix (optional)
random_file = pick_random_file(directory_path, subdirectories, filename_prefix)
print(f"Randomly selected file:=>  {random_file}")

# Example without a prefix:
random_file_no_prefix = pick_random_file(directory_path, subdirectories)
print(f"Randomly selected file without prefix: => {random_file_no_prefix}")



"""
.\array
.\backtracking
.\bfs_and_queue
.\binary_search
.\binary_tree
.\design
.\dfs_and_stack
.\divide_and_conquer
.\dynamic_programming
.\graph
.\greedy
.\hashmap
.\linked-lists
.\newDoneQuestions
.\priority_queue
.\strings
"""