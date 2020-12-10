package myProgressBar;

import java.text.NumberFormat;

import javax.swing.JProgressBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicProgressBarUI;

@SuppressWarnings("serial")
public class MyProgressBar extends JProgressBar {
 
    private transient NumberFormat numberFormat;
	
    private NumberFormat createPercentFormat(){
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(minFractionDigits()); // also affect MaximumFractionDigits
        nf.setMaximumFractionDigits(maxFractionDigits());
        return nf;
    }
    protected int minFractionDigits() {
        return 0;
    }
    protected int maxFractionDigits() {
        return 3;
    }
 
    @Override
    public String getString() {
        if (progressString != null) return progressString;
        if (numberFormat == null) {
            numberFormat = this.createPercentFormat();
        }
        return numberFormat.format( getPercentComplete() );
    }
 
    @Override
    public void updateUI() {
        super.setUI(new BasicProgressBarUI() {
            @Override
            protected void installListeners() {
                super.installListeners();
                super.progressBar.removeChangeListener(super.changeListener);
                super.changeListener = new ChangeListener() {
                    private final double fd = Math.pow(10, 2+maxFractionDigits());
                    private int cachedPercent = 0;
                    /**
                     * @see BasicProgressBarUI.Handler#stateChanged(ChangeEvent)
                     */
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        final int percent = (int) (getPercentComplete()*fd +0.5);
                        if(cachedPercent == percent) return; // ignore
                        cachedPercent = percent; // update newPercent
                        progressBar.repaint();
                    }
                };
                super.progressBar.addChangeListener(super.changeListener);
            }
        });
    }
}
