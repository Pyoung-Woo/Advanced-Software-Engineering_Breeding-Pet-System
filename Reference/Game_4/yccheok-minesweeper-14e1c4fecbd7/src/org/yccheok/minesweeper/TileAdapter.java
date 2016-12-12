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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TileAdapter extends ArrayAdapter<Tile> {
    private Context mContext;
    private List<Tile> tiles;
    
    public TileAdapter(Context c, List<Tile> tiles) {
        super(c, R.layout.tile, tiles);
        mContext = c;
        this.tiles = tiles;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            textView = (TextView)inflater.inflate(R.layout.tile, null);
        } else {
            textView = (TextView) convertView;
        }

        Tile tile = tiles.get(position);
        
        Tile.Status status = tile.getStatus();
        
        if (status == Tile.Status.Flag) {
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.flag, 0, 0, 0);
        } else if (status == Tile.Status.Opened) {
            
            if (tile.hasBomb()) {
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bomb, 0, 0, 0);
                return textView;
            }
            
            int size = tile.getNeighbourBombSize();
            textView.setBackgroundColor(0xffffffff);
            
            if (size == 0) {
                textView.setText("");
            } else {
                textView.setText("" + size);
                textView.setTextColor(colors[size - 1]);
            }
        } else if (status == Tile.Status.Normal) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        
        return textView;
    }
    
    final int[] colors = {0xff839098, 0xffF7D842, 0xffF76D3C, 0xffF15F74, 0xff913CCD, 0xff2CA8C2, 0xff98CB4A, 0xff5481E6};
}
