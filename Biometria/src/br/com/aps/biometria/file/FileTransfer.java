package br.com.aps.biometria.file;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.TransferHandler;

public class FileTransfer extends TransferHandler {

	private static final long serialVersionUID = 1L;

	private File file;
	private boolean fileOnDrag, dragEnabled;
	
	public FileTransfer() {
		fileOnDrag = false;
		dragEnabled = true;
	}

	@Override
	public boolean canImport(TransferSupport fileDrag) {
		if (!fileDrag.isDrop() || !isDragEnabled())
			return false;

		return fileDrag.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
	}

	@Override
	public boolean importData(TransferSupport fileDrag) {

		fileOnDrag = true;

		if (!canImport(fileDrag))
			return false;

		try {
			Transferable transferencia = fileDrag.getTransferable();
			Object dadosTransferencia = transferencia
					.getTransferData(DataFlavor.javaFileListFlavor);

			@SuppressWarnings("unchecked")
			List<File> itens = (List<File>) dadosTransferencia;

			if (!itens.get(0).getName().endsWith(".png")) {
				JOptionPane.showMessageDialog(null,
						"Extensões permitidas: \n- PNG ",
						"Autenticação de Imagem", JOptionPane.WARNING_MESSAGE);
				fileOnDrag = false;
				return false;
			}

			if (itens.get(0).length() > 2097152) {
				JOptionPane.showMessageDialog(null, "- O limite máximo é de "
						+ "2 MB.", "Autenticação de Imagem",
						JOptionPane.WARNING_MESSAGE);
				fileOnDrag = false;
				return false;
			}

			file = itens.get(0);

		} catch (UnsupportedFlavorException e) {
			return false;
		} catch (IOException e) {
			return false;
		}

		return true;
	}

	public File getFile() {
		return file;
	}

	public void removeFile() {
		file = null;
	}

	public boolean isFileOnDrag() {
		return fileOnDrag;
	}

	public void setFileOnDrag(boolean fileOnDrag) {
		this.fileOnDrag = fileOnDrag;
	}

	public boolean isDragEnabled() {
		return dragEnabled;
	}

	public void setDragEnabled(boolean dragEnabled) {
		this.dragEnabled = dragEnabled;
	}

}