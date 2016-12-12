/*
 * Copyright (C) 2013 Cheok Yan Cheng
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal 
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in 
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
 * SOFTWARE.
 */

package org.yccheok.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.yccheok.minesweeper.Tile.Status;

public class GameEngine {
    public enum Mode {
        Bomb,
        Flag
    }
    
    public enum Result {
        None,
        Win,
        Lost
    }
    
    public void newGame() {
        // Clear previous game.
        tiles.clear();
        
        flag = 0;
        
        List<Integer> titleIndices = new ArrayList<Integer>();
        
        // Basic initialization.
        for (int i = 0; i < COL_SIZE * ROW_SIZE; i++) {
            tiles.add(new Tile());
            titleIndices.add(i);
        }
        
        // Plant some bombs.
        assert(BOMB_SIZE <= titleIndices.size());
        for (int i = 0; i < BOMB_SIZE; i++) {
            int bombIndex = random.nextInt(titleIndices.size());
            tiles.get(titleIndices.get(bombIndex)).setBomb(true);
            titleIndices.remove((int)bombIndex);
        }
        
        // Figure out neighbour bomb size.
        for (int row = 0; row < ROW_SIZE; row++) {
            for (int col = 0; col < COL_SIZE; col++) {
                int neighbourBombSize = 0;
                
                // TOP ROW
                if ((row-1) >= 0 && (col-1) >= 0) {
                    if (getTile(row-1, col-1).hasBomb()) {
                        neighbourBombSize++;
                    }
                }
                
                if ((row-1) >= 0) {
                    if (getTile(row-1, col).hasBomb()) {
                        neighbourBombSize++;
                    }
                }

                if ((row-1) >= 0 && (col+1) < COL_SIZE) {
                    if (getTile(row-1, col+1).hasBomb()) {
                        neighbourBombSize++;
                    }
                }
                
                // SAME ROW
                if ((col-1) >= 0) {
                    if (getTile(row, col-1).hasBomb()) {
                        neighbourBombSize++;
                    }
                }

                if ((col+1) < COL_SIZE) {
                    if (getTile(row, col+1).hasBomb()) {
                        neighbourBombSize++;
                    }
                }
                
                // BOTTOM ROW
                if ((row+1) < ROW_SIZE && (col-1) >= 0) {
                    if (getTile(row+1, col-1).hasBomb()) {
                        neighbourBombSize++;
                    }
                }
                
                if ((row+1) < ROW_SIZE) {
                    if (getTile(row+1, col).hasBomb()) {
                        neighbourBombSize++;
                    }
                }

                if ((row+1) < ROW_SIZE && (col+1) < COL_SIZE) {
                    if (getTile(row+1, col+1).hasBomb()) {
                        neighbourBombSize++;
                    }
                } 
                
                getTile(row, col).setNeighbourBombSize(neighbourBombSize);
            }
        }
        
        // Switch to bomb mode.
    }
    
    public void toggleMode() {
        this.mode = this.mode == Mode.Bomb ? Mode.Flag : Mode.Bomb;
    }
    
    public Mode getMode() {
        return this.mode;
    }
    
    public void openAllTilesIfPossible(int position) {
        if (position < 0 || position >= tiles.size()) {
            return;
        }
        
        Tile tile = tiles.get(position);
        
        if (tile.getStatus() != Status.Normal) {
            return;
        }
        
        tile.setStatus(Status.Opened);
        
        int row = position / COL_SIZE;
        int col = position % COL_SIZE;
        
        if (tile.hasBomb() == false && tile.getNeighbourBombSize() == 0) {
            // TOP ROW
            if ((row-1) >= 0 && (col-1) >= 0) {
                int index = (row-1) * COL_SIZE + (col-1);
                openAllTilesIfPossible(index);
            }
            
            if ((row-1) >= 0) {
                int index = (row-1) * COL_SIZE + col;
                openAllTilesIfPossible(index);
            }

            if ((row-1) >= 0 && (col+1) < COL_SIZE) {
                int index = (row-1) * COL_SIZE + (col+1);
                openAllTilesIfPossible(index);
            }
            
            // SAME ROW
            if ((col-1) >= 0) {
                int index = row * COL_SIZE + (col-1);
                openAllTilesIfPossible(index);
            }

            if ((col+1) < COL_SIZE) {
                int index = row * COL_SIZE + (col+1);
                openAllTilesIfPossible(index);
            }
            
            // BOTTOM ROW
            if ((row+1) < ROW_SIZE && (col-1) >= 0) {
                int index = (row+1) * COL_SIZE + (col-1);
                openAllTilesIfPossible(index);
            }
            
            if ((row+1) < ROW_SIZE) {
                int index = (row+1) * COL_SIZE + col;
                openAllTilesIfPossible(index);
            }

            if ((row+1) < ROW_SIZE && (col+1) < COL_SIZE) {
                int index = (row+1) * COL_SIZE + (col+1);
                openAllTilesIfPossible(index);
            } 
        }
    }
    
    public Tile getTile(int row, int col) {
        int index = row * COL_SIZE + col;
        return tiles.get(index);
    }
    
    public List<Tile> getTiles() {
        return Collections.unmodifiableList(tiles);
    }
    
    public void incFlag() {
        flag++;
    }
    
    public void decFlag() {
        flag--;
    }
    
    public int getFlag() {
        return flag;
    }
    
    public int getBombSize() {
        return BOMB_SIZE;
    }
    
    public Result getResult() {
        boolean isAllOpenOrFlag = true;
        for (Tile tile : tiles) {
            Status status = tile.getStatus(); 
            if (status == Status.Opened) {
                if (tile.hasBomb()) {
                    return Result.Lost;
                }
            } else if (status == Status.Normal) {
                isAllOpenOrFlag = false;
            }
        }
        if (isAllOpenOrFlag) {
            return Result.Win;
        }
        return Result.None;
    }
    
    public void cheat() {
        for (Tile tile : tiles) {
            if (tile.hasBomb()) {
                tile.setStatus(Status.Flag);
            } else {
                tile.setStatus(Status.Opened);
            }
        }
    }
    private static final int COL_SIZE = 8;
    private static final int ROW_SIZE = 8;
    private static final int BOMB_SIZE = 10;
    private final List<Tile> tiles = new ArrayList<Tile>();
    private final Random random = new Random();
    private Mode mode = Mode.Bomb;
    private int flag = 0;
}
