try:
    from PIL import Image
except ImportError:
    import Image

img = Image.open("./MathPic.ppm")
#img.save("./1.jpg")
img.show()
