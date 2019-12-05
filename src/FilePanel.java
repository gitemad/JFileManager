import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileSystemView;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author Emad
 *
 */
public class FilePanel extends JPanel {

    private int x;
    private int y;
    private int x2;
    private int y2;
    private static int gap = 15;
    private JPopupMenu rClickMenu = new ContextMenuPanel();
    private FileSystemView fileSystemView;
    private ArrayList<JLabel> fileLabels;



    
    public FilePanel() {
    	super(new WrapLayout(WrapLayout.LEFT, gap, gap));
        x = y = x2 = y2 = 0;
        MyMouseListener listener = new MyMouseListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        
        fileSystemView = FileSystemView.getFileSystemView();
        File[] roots = fileSystemView.getRoots();
		for (File fileSystemRoot : roots) {
			File[] files = fileSystemView.getFiles(fileSystemRoot, true);
			fileLabels = new ArrayList<JLabel>();
			for (File file : files) {
				JLabel fileLabel = new JLabel();
				fileLabels.add(fileLabel);
				fileLabel.setPreferredSize(new Dimension(130, 130));
				//TODO
//				fileLabel.setToolTipText();
				fileLabel.setHorizontalAlignment(JLabel.CENTER);
				fileLabel.setVerticalAlignment(JLabel.BOTTOM);
				fileLabel.setHorizontalTextPosition(JLabel.CENTER);
				fileLabel.setVerticalTextPosition(JLabel.BOTTOM);
				ImageIcon icon = (ImageIcon) fileSystemView.getSystemIcon(file);
				Image imgIcon = icon.getImage();
				try {
					imgIcon = sun.awt.shell.ShellFolder.getShellFolder(file).getIcon(true);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				imgIcon = getScaledImage(imgIcon, 100, 100);
				icon.setImage(imgIcon);
				fileLabel.setIcon(icon);
				fileLabel.setText(fileSystemView.getSystemDisplayName(file));
				
				
				fileLabel.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						fileLabel.setOpaque(false);
						repaint();
					}
					
					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						fileLabel.setOpaque(true);
						fileLabel.setBackground(getBackground().darker());
						repaint();
					}
					
					@Override
					public void mouseClicked(MouseEvent arg0) {
						deselectAll();
						fileLabel.setOpaque(true);
						fileLabel.setBackground(getBackground().darker().darker());
						repaint();
					}
				});
				
				this.add(fileLabel);
			}
		}
		
    }

    public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setEndPoint(int x, int y) {
        x2 = (x);
        y2 = (y);
    }

    public void drawPerfectRect(Graphics g, int x, int y, int x2, int y2) {
        int px = Math.min(x,x2);
        int py = Math.min(y,y2);
        int pw = Math.abs(x-x2);
        int ph = Math.abs(y-y2);
        g.fillRect(px, py, pw, ph);
    }
    
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    
    private void deselectAll() {
    	for (JLabel fileLabel : fileLabels) {
    		fileLabel.setOpaque(false);
    	}
    }
    
    
    class MyMouseListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            setStartPoint(e.getX(), e.getY());
        }

        public void mouseDragged(MouseEvent e) {
            setEndPoint(e.getX(), e.getY());
            repaint();
        }

        public void mouseReleased(MouseEvent e) {
            x = y = x2 = y2 = 10000;
            repaint();
            if (e.getButton() == MouseEvent.BUTTON3) {
            	rClickMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.clearRect(0, 0, getX(), getY());
        g.setColor(new Color(0, 0, 255, 100));
        drawPerfectRect(g, x, y, x2, y2);
    }
    
}
    class WrapLayout extends FlowLayout {

        private Dimension preferredLayoutSize;

        /**
         * Constructs a new
         * <code>WrapLayout</code> with a left alignment and a default 5-unit
         * horizontal and vertical gap.
         */
        public WrapLayout() {
            super();
        }

        /**
         * Constructs a new
         * <code>FlowLayout</code> with the specified alignment and a default 5-unit
         * horizontal and vertical gap. The value of the alignment argument must be
         * one of
         * <code>WrapLayout</code>,
         * <code>WrapLayout</code>, or
         * <code>WrapLayout</code>.
         *
         * @param align the alignment value
         */
        public WrapLayout(int align) {
            super(align);
        }

        /**
         * Creates a new flow layout manager with the indicated alignment and the
         * indicated horizontal and vertical gaps.
         * <p>
         * The value of the alignment argument must be one of
         * <code>WrapLayout</code>,
         * <code>WrapLayout</code>, or
         * <code>WrapLayout</code>.
         *
         * @param align the alignment value
         * @param hgap the horizontal gap between components
         * @param vgap the vertical gap between components
         */
        public WrapLayout(int align, int hgap, int vgap) {
            super(align, hgap, vgap);
        }

        /**
         * Returns the preferred dimensions for this layout given the
         * <i>visible</i> components in the specified target container.
         *
         * @param target the component which needs to be laid out
         * @return the preferred dimensions to lay out the subcomponents of the
         * specified container
         */
        @Override
        public Dimension preferredLayoutSize(Container target) {
            return layoutSize(target, true);
        }

        /**
         * Returns the minimum dimensions needed to layout the <i>visible</i>
         * components contained in the specified target container.
         *
         * @param target the component which needs to be laid out
         * @return the minimum dimensions to lay out the subcomponents of the
         * specified container
         */
        @Override
        public Dimension minimumLayoutSize(Container target) {
            Dimension minimum = layoutSize(target, false);
            minimum.width -= (getHgap() + 1);
            return minimum;
        }

        /**
         * Returns the minimum or preferred dimension needed to layout the target
         * container.
         *
         * @param target target to get layout size for
         * @param preferred should preferred size be calculated
         * @return the dimension to layout the target container
         */
        private Dimension layoutSize(Container target, boolean preferred) {
            synchronized (target.getTreeLock()) {
                //  Each row must fit with the width allocated to the containter.
                //  When the container width = 0, the preferred width of the container
                //  has not yet been calculated so lets ask for the maximum.

                int targetWidth = target.getSize().width;

                if (targetWidth == 0) {
                    targetWidth = Integer.MAX_VALUE;
                }

                int hgap = getHgap();
                int vgap = getVgap();
                Insets insets = target.getInsets();
                int horizontalInsetsAndGap = insets.left + insets.right + (hgap * 2);
                int maxWidth = targetWidth - horizontalInsetsAndGap;

                //  Fit components into the allowed width

                Dimension dim = new Dimension(0, 0);
                int rowWidth = 0;
                int rowHeight = 0;

                int nmembers = target.getComponentCount();

                for (int i = 0; i < nmembers; i++) {
                    Component m = target.getComponent(i);

                    if (m.isVisible()) {
                        Dimension d = preferred ? m.getPreferredSize() : m.getMinimumSize();

                        //  Can't add the component to current row. Start a new row.

                        if (rowWidth + d.width > maxWidth) {
                            addRow(dim, rowWidth, rowHeight);
                            rowWidth = 0;
                            rowHeight = 0;
                        }

                        //  Add a horizontal gap for all components after the first

                        if (rowWidth != 0) {
                            rowWidth += hgap;
                        }

                        rowWidth += d.width;
                        rowHeight = Math.max(rowHeight, d.height);
                    }
                }

                addRow(dim, rowWidth, rowHeight);

                dim.width += horizontalInsetsAndGap;
                dim.height += insets.top + insets.bottom + vgap * 2;

                //    When using a scroll pane or the DecoratedLookAndFeel we need to
                //  make sure the preferred size is less than the size of the
                //  target containter so shrinking the container size works
                //  correctly. Removing the horizontal gap is an easy way to do this.

                Container scrollPane = SwingUtilities.getAncestorOfClass(JScrollPane.class, target);

                if (scrollPane != null && target.isValid()) {
                    dim.width -= (hgap + 1);
                }

                return dim;
            }
        }

        /*
         *  A new row has been completed. Use the dimensions of this row
         *  to update the preferred size for the container.
         *
         *  @param dim update the width and height when appropriate
         *  @param rowWidth the width of the row to add
         *  @param rowHeight the height of the row to add
         */
        private void addRow(Dimension dim, int rowWidth, int rowHeight) {
            dim.width = Math.max(dim.width, rowWidth);

            if (dim.height > 0) {
                dim.height += getVgap();
            }

            dim.height += rowHeight;
        }
    }
    