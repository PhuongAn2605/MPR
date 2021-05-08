package edu.hanu.nfrs.tictactoe;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.hanu.nfrs.R;

public class TicTacToeFragment extends Fragment implements View.OnClickListener {
    private Symbol currentPlayer;
    private Board board;
    private TextView tvCurrentPlayer;
    private GridLayout layoutBoard;
    private List<ImageView> cells;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);

        // refs
        tvCurrentPlayer = v.findViewById(R.id.tvCurrentPlayer);
        layoutBoard = v.findViewById(R.id.layoutBoard);

        cells = new ArrayList<>();
        for (int i = 0; i < layoutBoard.getChildCount(); i++) {
            ImageView child = (ImageView) layoutBoard.getChildAt(i);
            cells.add(child);
            child.setOnClickListener(this);
        }

        // game
        this.currentPlayer = Symbol.X;
        tvCurrentPlayer.setText(this.currentPlayer.toString());

        this.board = new Board();
        drawBoard();

        // exit
        Button btnExit = v.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
            }
        });

        return v;
    }

    private void drawBoard() {
        for (int i = 0; i < 9; i++) {
            Symbol symbol = this.board.get(i);
            ImageView imageView = (ImageView) layoutBoard.getChildAt(i);

            Bitmap bitmap = getBitmapForSymbol(symbol);
            imageView.setImageBitmap(bitmap);
        }
    }

    private Bitmap getBitmapForSymbol(Symbol symbol) {
        Resources res = getResources();
        Bitmap bitmap;

        if (symbol == null) {
            bitmap = BitmapFactory.decodeResource(res, R.drawable.blank);
        } else if (symbol == Symbol.X) {
            bitmap = BitmapFactory.decodeResource(res, R.drawable.x);
        } else {
            bitmap = BitmapFactory.decodeResource(res, R.drawable.o);
        }

        return bitmap;
    }

    @Override
    public void onClick(View v) {
        int x = this.cells.indexOf(v);

        if (!board.set(this.currentPlayer, x)) {
            return;
        }

        drawBoard();

        // check winner
        if (board.hasWinner()) {
            Toast.makeText(getActivity(), this.currentPlayer + " won!", Toast.LENGTH_LONG).show();
            return;
        }

        // check draw
        if (!board.hasBlank()) {
            Toast.makeText(getActivity(), "Draw!", Toast.LENGTH_LONG).show();
            return;
        }

        // switch player
        this.currentPlayer = this.currentPlayer == Symbol.X ? Symbol.O : Symbol.X;
        tvCurrentPlayer.setText(this.currentPlayer.toString());
    }
}