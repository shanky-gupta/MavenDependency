package com.example.bottomlayout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by Anton Bevza on 5/5/16.
 */
public class TabButton extends RelativeLayout {
    private TextView textView;
    private ImageView imageView;
    private ClickListener listener;
    //Text view for show bubble like unread massage
    private TextView tvBubble;
    private View containerBubble;


    public TabButton(Context context) {
        super(context);
        init();
    }

    public TabButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Initialize tab button
     */
    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.part_view_tab_button, this, true);
        imageView = (ImageView) v.findViewById(R.id.icon);
        textView = (TextView) v.findViewById(R.id.text);
        tvBubble = (TextView) v.findViewById(R.id.bubble);
        containerBubble = v.findViewById(R.id.containerBubble);
    }

    /**
     * Set button icon. Icon show as drawable top
     *
     * @param drawable drawable
     */
    public void setIcon(Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    /**
     * Set title text
     *
     * @param title title
     */
    public void setText(String title) {
        if (title != null && title.length() > 0) {
            textView.setText(title);
        } else {
            textView.setVisibility(GONE);
        }
    }

    /**
     * Text view for show bubble like unread massage
     *
     * @param count count of unread massages
     */
    public void setUnreadCount(int count) {
        tvBubble.setText("" + count);
        tvBubble.setVisibility(count > 0 ? VISIBLE : GONE);
    }


    /**
     * Set selected button
     *
     * @param selected value
     */
    public void setSelected(boolean selected) {
        imageView.setSelected(selected);
        textView.setSelected(selected);
    }

    /**
     * Set button click listener
     *
     * @param clickListener ClickListener interface
     */
    public void setListener(ClickListener clickListener) {
        listener = clickListener;
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick();
                }
            }
        });
    }

    /**
     * Set button text style
     *
     * @param res Style res id
     */
    public void setButtonTextStyle(@StyleRes int res) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textView.setTextAppearance(res);
        } else {
            textView.setTextAppearance(getContext(), res);
        }
    }


    /**
     * Set unread bubble color
     *
     * @param color
     */
    public void setBubbleColor(int color) {
        GradientDrawable bgShape = (GradientDrawable) tvBubble.getBackground().getCurrent();
        bgShape.setColor(color);
    }

    /**
     * Set unread bubble padding
     *
     * @param left   px
     * @param top    px
     * @param right  px
     * @param bottom px
     */
    public void setBubblePadding(int left, int top, int right, int bottom) {
        containerBubble.setPadding(left, top, right, bottom);
    }

    /**
     * Set unread bubble text style
     *
     * @param res Style res id
     */
    public void setBubbleTextStyle(@StyleRes int res) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tvBubble.setTextAppearance(res);
        } else {
            tvBubble.setTextAppearance(getContext(), res);
        }
    }

    /**
     * Interface for click listener
     */
    public interface ClickListener {
        void onClick();
    }
}
