import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileSystemView;

import Controller.FileLabelController;
import Model.FileLabelModel;
import View.FileLabelView;

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

//    private int x;
//    private int y;
//    private int x2;
//    private int y2;
    private RectangleDrawer rectDrawer;
    private static int gap = 15;
    private JPopupMenu rClickMenuPane;
    private FileSystemView fileSystemView;
    private ArrayList<FileLabelModel> fileLabelsModel;
    private ArrayList<FileLabelView> fileLabelsView;
    private ArrayList<FileLabelController> fileLabelsController;
    private DrawMouseListener drawMouseListener;



    
    public FilePanel() {
    	super(new WrapLayout(WrapLayout.LEFT, gap, gap));
    	rClickMenuPane = new ContextMenuEmptyPanel();
    	rectDrawer = new RectangleDrawer();
    	
//        x = y = x2 = y2 = 0;
    	drawMouseListener = new DrawMouseListener();
        addMouseListener(drawMouseListener);
        addMouseMotionListener(drawMouseListener);
        
        fileSystemView = FileSystemView.getFileSystemView();
        File[] roots = fileSystemView.getRoots();
		for (File fileSystemRoot : roots) {
			File[] files = fileSystemView.getFiles(fileSystemRoot, true);
			fileLabelsModel = new ArrayList<FileLabelModel>();
			fileLabelsView = new ArrayList<FileLabelView>();
			fileLabelsController = new ArrayList<FileLabelController>();
			for (File file : files) {
				FileLabelModel fileLabelModel = new FileLabelModel(file);
				FileLabelView fileLabelView = new FileLabelView(fileLabelModel);
				FileLabelController fileLabelController = new FileLabelController(fileLabelModel, fileLabelView);
				fileLabelsModel.add(fileLabelModel);				
				fileLabelsView.add(fileLabelView);				
				fileLabelsController.add(fileLabelController);				
				
				fileLabelView.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if (e.isPopupTrigger()) {
			            	fileLabelView.getRightClickMenu().show(e.getComponent(), e.getX(), e.getY());
			            }
					}
					
					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						deselectAll();
						fileLabelController.selected();
						repaint();
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						if (!fileLabelModel.isClicked()) {
							fileLabelController.unSelected();
							repaint();
						}
					}
					
					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						if (!fileLabelModel.isClicked()) {
							fileLabelController.hover();
							repaint();
						}
					}
					
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
					}
				});
				
				this.add(fileLabelView);
			}
		}
		
    }
    
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    
    //TODO
    private void deselectAll() {
    	for (FileLabelController fileLabel : fileLabelsController) {
    		fileLabel.unSelected();
    	}
    }
    
    
    class DrawMouseListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            rectDrawer.setStartPoint(e.getX(), e.getY());
            deselectAll();
        }

        public void mouseDragged(MouseEvent e) {
            rectDrawer.setEndPoint(e.getX(), e.getY());
            repaint();
        }

        public void mouseReleased(MouseEvent e) {
        	rectDrawer.setStartPoint(10000, 10000);
        	rectDrawer.setEndPoint(10000, 10000);
        	repaint();
            if (e.getButton() == MouseEvent.BUTTON3) {
            	rClickMenuPane.show(e.getComponent(), e.getX(), e.getY());
            }
        }
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = rectDrawer.getX();
        int y = rectDrawer.getY();
        int x2 = rectDrawer.getX2();
        int y2 = rectDrawer.getY2();
//        g.clearRect(0, 0, getX(), getY());
        g.setColor(new Color(0, 0, 255));
        rectDrawer.drawRectBorder(g, x, y, x2, y2);
        g.setColor(new Color(0, 0, 255, 100));
        rectDrawer.fillRect(g, x, y, x2, y2);
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
    