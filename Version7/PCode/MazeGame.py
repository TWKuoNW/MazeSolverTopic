from pygame.locals import *
import pygame
import time

#角色控制端
class Player:
    x = 0
    y = 0
    speed = 44
    currentPosition = [0,0]
    
    
    def moveRight(self):
        self.x = self.x + self.speed  # 往右移動
        self.currentPosition[0] = self.currentPosition[0] + 1
 
    def moveLeft(self):
        self.x = self.x - self.speed  # 往左移動
        self.currentPosition[0] = self.currentPosition[0] - 1
 
    def moveUp(self):
        self.y = self.y - self.speed  # 往上移動
        self.currentPosition[1] = self.currentPosition[1] - 1
 
    def moveDown(self):
        self.y = self.y + self.speed  # 往下移動
        self.currentPosition[1] = self.currentPosition[1] + 1
 
#地圖
class Maze:
    arrayMark = []
    def __init__(self):
        filename = 'Maze\\1output.txt'  # 檔案名稱或路徑
        # 讀取陣列
        array = []
        # 初始化空的二維陣列
        flattened_array = []
        #==============================
        # 開啟檔案並讀取內容
        with open(filename, 'r') as f:
            lines = f.readlines()

        # 逐行處理檔案內容
        for line in lines:
            # 移除每行前後的空白字符並分割成元素列表
            es = line.strip().split()
            
            # 將元素轉換為整數並添加到二維陣列
            r = [int(e) for e in es]
            self.arrayMark.append(r)        
        #==============================

        with open(filename, 'r', encoding='utf-8', errors='ignore') as file:
            for line in file:
                line = line.strip()  # 移除每行前後的空白字符
                elements = line.split()  # 按空白字符分割成元素列表
                row = [int(element) for element in elements]  # 將元素轉換為整數
                array.append(row)  # 添加到陣列中

        for row in array:
            for element in row:
                flattened_array.append(element)

        self.maze = flattened_array  # 迷宮陣列

        self.M = int(len(flattened_array)**0.5)  # 迷宮的寬度
        self.N = int(len(flattened_array)**0.5)  # 迷宮的高度
        
    #繪圖
    def draw(self, display_surf, image_surf):
        bx = 0
        by = 0
        
        for i in range(0, self.M * self.N):
            if self.maze[bx + (by * self.M)] == 1:
                display_surf.blit(image_surf, (bx * 44, by * 44))  # 繪製牆壁方塊
                
            bx = bx + 1
            if bx > self.M - 1:
                bx = 0 
                by = by + 1
 
#遊戲
class App:
    windowWidth = 0
    windowHeight = 0
    player = 0
 
    def __init__(self):
        self._running = True
        self._display_surf = None
        self._image_surf = None
        self._block_surf = None
        self.player = Player()
        self.maze = Maze()

        self.windowWidth = 44 * self.maze.M
        self.windowHeight = 44 * self.maze.N
 
    def on_init(self):
        pygame.init()
        self._display_surf = pygame.display.set_mode((self.windowWidth, self.windowHeight), pygame.HWSURFACE)
 
        pygame.display.set_caption('Pygame pythonspot.com example')
        self._running = True
        self._image_surf = pygame.transform.scale(pygame.image.load("imageFolder\\player.png"), (44, 44)).convert()
        self._block_surf = pygame.transform.scale(pygame.image.load("imageFolder\\block.png"), (44, 44)).convert()
 
    def on_event(self, event):
        if event.type == QUIT:
            self._running = False
 
    def on_loop(self):
        pass
 
    def on_render(self):
        self._display_surf.fill((0, 0, 0))  # 填滿背景顏色
        self._display_surf.blit(self._image_surf, (self.player.x, self.player.y))  # 繪製玩家
        self.maze.draw(self._display_surf, self._block_surf)  # 繪製迷宮
        pygame.display.flip()  # 更新顯示視窗
 
    def on_cleanup(self):
        pygame.quit()
 
    def on_execute(self):
        if self.on_init() == False:
            self._running = False

        #print(len(self.maze.arrayMark)) 
        move = pygame.mixer.Sound("music\\move.mp3")
        victory = pygame.mixer.Sound("music\\Victory.mp3")
        BGM = pygame.mixer.Sound("music\\BGM.mp3")
        BGM.set_volume(0.5)
        BGM.play()
        while self._running:
            pygame.event.pump()
            keys = pygame.key.get_pressed()
            #nowPosition = self.maze.arrayMark[self.player.currentPosition[1]][self.player.currentPosition[0]]
            
            upPosition = self.maze.arrayMark[self.player.currentPosition[1]-1][self.player.currentPosition[0]]
            leftPosition = self.maze.arrayMark[self.player.currentPosition[1]][self.player.currentPosition[0]-1]
            
            if(self.player.currentPosition[0] != self.maze.M-1):
                rightPosition = self.maze.arrayMark[self.player.currentPosition[1]][self.player.currentPosition[0]+1]
            
            if(self.player.currentPosition[1] != self.maze.N-1):
                downPosition = self.maze.arrayMark[self.player.currentPosition[1]+1][self.player.currentPosition[0]]
            
            if(self.player.currentPosition[0] == self.maze.M-1):#監測是否在最右邊
                rightPosition = 1

            if(self.player.currentPosition[0] == 0):#監測是否在最左邊
                leftPosition = 1

            if(self.player.currentPosition[1] == 0):#監測是否在最上邊
                upPosition = 1

            if(self.player.currentPosition[1] == self.maze.N-1):#監測是否在最下邊
                downPosition = 1

            #print(self.player.currentPosition[0])#當前x座標位置
            #print(self.player.currentPosition[1])#當前y座標位置
            #print(self.maze.M-1)#總寬度
            #print(self.maze.N-1)#總長度

            print("up:",upPosition," down:",downPosition," right:",rightPosition," left:",leftPosition) #顯示當前位置周圍是否有障礙

            #print("x:", self.player.x, " y:", self.player.y)
            #print("currentPosition:", self.player.currentPosition)

            if (keys[K_RIGHT]  and rightPosition != 1):
                self.player.moveRight()  # 玩家向右移動
                move.play()
                time.sleep(0.3)
 
            if (keys[K_LEFT]  and leftPosition != 1):
                self.player.moveLeft()  # 玩家向左移動
                move.play()
                time.sleep(0.3)
 
            if (keys[K_UP]  and upPosition != 1):
                self.player.moveUp()  # 玩家向上移動
                move.play()
                time.sleep(0.3)
 
            if (keys[K_DOWN] and downPosition != 1):
                self.player.moveDown()  # 玩家向下移動
                move.play()
                time.sleep(0.3)
 
            if keys[K_ESCAPE]:
                self._running = False  # 按下 ESC 鍵結束程式

            self.on_loop()
            self.on_render()

            if self.player.x == Maze().M * 44 - 44 and self.player.y == Maze().N * 44 - 44: # 玩家到達迷宮出口
                print("winner")
                victory.play()
                time.sleep(3)
                self._running = False  
        self.on_cleanup()
 
if __name__ == "__main__":
    theApp = App()
    theApp.on_execute()
