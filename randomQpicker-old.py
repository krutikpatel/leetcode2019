import os

def map_files_in_subdirectories(directory):
    dir_file_map = {}
    for root, dirs, files in os.walk(directory):
        if files:
            dir_file_map[root] = files
    return dir_file_map

# Usage
directory = '.'

    
files = map_files_in_subdirectories(directory)
for directory2, file_list in files.items():
    print("Directory:", directory2)
    for file in file_list:
        #print("File:", file)
        print(file)