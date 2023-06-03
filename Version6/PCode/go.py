from pygame.locals import *
import pygame

class Player:
    x = 0
    y = 0
    speed = 1
 
    def moveRight(self):
        self.x = self.x + self.speed
 
    def moveLeft(self):
        self.x = self.x - self.speed
 
    def moveUp(self):
        self.y = self.y - self.speed
 
    def moveDown(self):
        self.y = self.y + self.speed
    
    

 
class Maze:
    rect = []
    def __init__(self):
        filename = 'Maze\\1output.txt'  # 替換為您的檔案名稱或路徑
        # 讀取陣列
        self.M = 11
        self.N = 11
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

        self.maze = flattened_array
 
    def draw(self,display_surf,image_surf):
        bx = 0
        by = 0
        
        for i in range(0,self.M*self.N):
            if self.maze[ bx + (by*self.M) ] == 1:
                display_surf.blit(image_surf,( bx * 44 , by * 44))
                
            bx = bx + 1
            if bx > self.M-1:
                bx = 0 
                by = by + 1
 
 
class App:
 
    windowWidth = 44 * Maze().M
    windowHeight =44 * Maze().N
    player = 0
    rect = Maze().rect
 
    def __init__(self):
        self._running = True
        self._display_surf = None
        self._image_surf = None
        self._block_surf = None
        self.player = Player()
        self.maze = Maze()
 
    def on_init(self):
        pygame.init()
        self._display_surf = pygame.display.set_mode((self.windowWidth,self.windowHeight), pygame.HWSURFACE)
 
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
        self._display_surf.fill((0,0,0))
        self._display_surf.blit(self._image_surf,(self.player.x,self.player.y))
        self.maze.draw(self._display_surf, self._block_surf)
        pygame.display.flip()
 
    def on_cleanup(self):
        pygame.quit()
 
    def on_execute(self):
        if self.on_init() == False:
            self._running = False
 
        while( self._running ):
            pygame.event.pump()
            keys = pygame.key.get_pressed()
 
            if (keys[K_RIGHT]):
                self.player.moveRight()
 
            if (keys[K_LEFT]):
                self.player.moveLeft()
 
            if (keys[K_UP]):
                self.player.moveUp()
 
            if (keys[K_DOWN]):
                self.player.moveDown()
 
            if (keys[K_ESCAPE]):
                self._running = False

            print("x:",self.player.x," y",self.player.y)
            if (self.player.x >= Maze().M*44-44 and self.player.y >= Maze().N*44-44 ):
                self._running = False

            self.on_loop()
            self.on_render()
        self.on_cleanup()
 
if __name__ == "__main__" :
    theApp = App()
    theApp.on_execute()