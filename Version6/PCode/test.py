filename = 'Maze\\1output.txt'  # 替換為您的檔案名稱或路徑

array = []
with open(filename, 'r', encoding='utf-8', errors='ignore') as file:
    for line in file:
        line = line.strip()  # 移除每行前後的空白字符
        elements = line.split()  # 按空白字符分割成元素列表
        row = [int(element) for element in elements]  # 將元素轉換為整數
        array.append(row)  # 添加到陣列中

flattened_array = []
for row in array:
    for element in row:
        flattened_array.append(element)

print(flattened_array)

