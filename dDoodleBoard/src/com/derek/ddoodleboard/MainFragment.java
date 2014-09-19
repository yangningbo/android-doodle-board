package com.derek.ddoodleboard;

import android.app.Fragment;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.derek.ddoodleboard.view.PaintView;

public class MainFragment extends Fragment {
	private PaintView mPaintView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);

		mPaintView = (PaintView) rootView.findViewById(R.id.view_paint_view);

		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.fragment_main, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.menu_item_clear) {
			mPaintView.clear();
			return true;
		} else if (id == R.id.menu_item_save) {
			Bitmap bitmap = mPaintView.getBitmap();

			ContentResolver cr = getActivity().getContentResolver();
			MediaStore.Images.Media.insertImage(cr, bitmap, "myPhoto", "this is a Photo");

			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
