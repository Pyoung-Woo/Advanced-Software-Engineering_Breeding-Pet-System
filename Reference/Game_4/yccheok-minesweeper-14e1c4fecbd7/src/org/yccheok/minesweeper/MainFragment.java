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

import java.util.List;

import org.yccheok.minesweeper.GameEngine.Mode;
import org.yccheok.minesweeper.GameEngine.Result;
import org.yccheok.minesweeper.Tile.Status;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainFragment extends Fragment {
    public static MainFragment newInstance() {
        return new MainFragment();
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        gameEngine.newGame();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        
        final View view = inflater.inflate(R.layout.main_fragment, container, false);
        this.newGameTextView = (TextView)view.findViewById(R.id.new_game_text_view);
        this.bombBalanceTextView = (TextView)view.findViewById(R.id.bomb_balance_text_view); 
        final ImageView modeImageView = (ImageView)view.findViewById(R.id.mode_image_view);
        this.gridView = (GridView)view.findViewById(R.id.grid_view);
        
        List<Tile> tiles = gameEngine.getTiles();
        TileAdapter tileAdapter = new TileAdapter(this.getActivity(), tiles);
        gridView.setAdapter(tileAdapter);
        
        newGameTextView.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                gameOver = false;
                gameEngine.newGame();
                bombBalanceTextView.setText("" + (gameEngine.getBombSize() - gameEngine.getFlag()));
                newGameTextView.setText("(^_^)");
                modeImageView.setImageResource(gameEngine.getMode() == GameEngine.Mode.Flag ? R.drawable.flag : R.drawable.bomb);
                TileAdapter tileAdapter = new TileAdapter(MainFragment.this.getActivity(), gameEngine.getTiles());
                gridView.setAdapter(tileAdapter);
            }
        });
        
        
        modeImageView.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                gameEngine.toggleMode();
                modeImageView.setImageResource(gameEngine.getMode() == GameEngine.Mode.Flag ? R.drawable.flag : R.drawable.bomb);
            }
        });

        gridView.setOnTouchListener(new View.OnTouchListener() {
            
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (gameOver) {
                    // Game over.
                    return false;
                }
                
                int actionMasked = event.getActionMasked();
                
                if (actionMasked == MotionEvent.ACTION_DOWN) {
                    if (gameEngine.getMode() == Mode.Bomb) {
                        newGameTextView.setText("(>_<)");
                    }
                } else if (actionMasked == MotionEvent.ACTION_UP) {
                    newGameTextView.setText("(^_^)");
                }
                // TODO Auto-generated method stub
                return false;
            }
        });
        
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long arg3) {
                final TileAdapter tileAdapter = (TileAdapter)gridView.getAdapter();
                Tile tile = tileAdapter.getItem(position);
                Tile.Status status = tile.getStatus();
                
                if (status == Tile.Status.Opened) {
                    return;
                }

                if (gameOver) {
                    // Game over.
                    return;
                }
                
                if (gameEngine.getMode() == Mode.Flag) {
                    if (status == Tile.Status.Flag) {
                        tile.setStatus(Status.Normal);
                        tileAdapter.notifyDataSetChanged();
                        gameEngine.decFlag();
                        bombBalanceTextView.setText("" + (gameEngine.getBombSize() - gameEngine.getFlag()));
                        return;
                    }
                    
                    gameEngine.incFlag();
                    final int remain = (gameEngine.getBombSize() - gameEngine.getFlag());
                    bombBalanceTextView.setText("" + remain);
                    tile.setStatus(Status.Flag);
                    tileAdapter.notifyDataSetChanged();
                    
                    if (remain == 0) {
                        Result result = gameEngine.getResult(); 
                        if (result == Result.Win) {
                            newGameTextView.setText("(^v^)");
                            gameOver = true;
                        } else if (result == Result.Lost) {
                            newGameTextView.setText("(+_+)");
                            gameOver = true;
                        } 
                    }
                } else {
                    if (status == Tile.Status.Flag) {
                        return;
                    }
                    
                    gameEngine.openAllTilesIfPossible(position);
                    //tile.setStatus(Status.Opened);
                    tileAdapter.notifyDataSetChanged();
                    
                    Result result = gameEngine.getResult(); 
                    if (result == Result.Win) {
                        newGameTextView.setText("(^v^)");
                        gameOver = true;
                    } else if (result == Result.Lost) {
                        newGameTextView.setText("(+_+)");
                        gameOver = true;
                    } 
                }
            }
        });
        
        return view;
    }
    
    public void cheat() {
        gameEngine.cheat();
        bombBalanceTextView.setText("0");
        newGameTextView.setText("(^v^)");
        gameOver = true;
        ((TileAdapter)gridView.getAdapter()).notifyDataSetChanged();
    }
    
    private boolean gameOver = false;
    private GameEngine gameEngine = new GameEngine();
    private TextView newGameTextView;
    private TextView bombBalanceTextView;
    private GridView gridView;
    
}
