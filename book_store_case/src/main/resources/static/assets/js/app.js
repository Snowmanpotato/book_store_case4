

class App {
    static DOMAIN_SERVER = "http://localhost:8080";
    static PRODUCT_API = this.DOMAIN_SERVER + "/api/products";
    static AUTH_URL = this.DOMAIN_SERVER + "/api/auth";
    static ROLE_API = this.DOMAIN_SERVER + "/api/roles";
    static ERROR_URL = this.DOMAIN_SERVER + "/error/";
    static PRODUCT_AVATAR_URL = this.DOMAIN_SERVER + "/api/avatar_images";



    static BASE_URL_CLOUD_IMAGE = "https://res.cloudinary.com/dypsc0wxd/image/upload";
    static BASE_SCALE_IMAGE = "c_limit,w_250,h_100,q_100";
    static SCALE_IMAGE_W600_600_Q100 = "c_limit,w_600,h_600,q_100";


    static SweetAlert = class {
        static showDeactivateConfirmDialog() {
            return Swal.fire({
                icon: 'warning',
                text: 'Are you sure to deactivate the selected product ?',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, please deactivate this client !',
                cancelButtonText: 'Cancel',
            })
        }

        static showSuccessAlert(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'top-end',
                showConfirmButton: false,
                timer: 1500
            })
        }


        static showErrorAlert(t) {
            Swal.fire({
                icon: 'error',
                title: 'Warning',
                text: t,
            })
        }

        static showError401() {
            Swal.fire({
                icon: 'error',
                title: 'Access Denied',
                text: 'Invalid credentials!',
            })
        }

        static showError403() {
            Swal.fire({
                icon: 'error',
                title: 'Access Denied',
                text: 'You are not authorized to perform this function!',
            })
        }

        static showError500() {
            Swal.fire({
                icon: 'error',
                title: 'Internal Server Error',
                text: 'The server system is having problems or is not accessible!',
            })
        }

        static redirectPage(message1, message2, timer) {
            let timerInterval;
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Logged in successfully!!',
                showConfirmButton: false,
                timer: 1500,
                timerProgressBar: true,
                didOpen: () => {
                    Swal.showLoading()
                    const b = Swal.getHtmlContainer().querySelector('b')
                    timerInterval = setInterval(() => {
                        b.textContent = Swal.getTimerLeft()
                    }, 100)
                },
                willClose: () => {
                    clearInterval(timerInterval);
                }
            }).then((result) => {
                if (result.dismiss === Swal.DismissReason.timer) {
                    console.log('I was closed by the timer')
                }
            })
        }
    }

    static IziToast = class {
        static showSuccessAlertLeft(m) {
            iziToast.success({
                title: 'OK',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }

        static showSuccessAlertRight(m) {
            iziToast.success({
                title: 'OK',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }

        static showErrorAlertLeft(m) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }

        static showErrorAlertRight(m) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }

    }
}

class ProductAvatar{
    constructor(id, fileName, fileFolder, fileUrl, fileType, cloudId, ts, product) {
        this.id = id;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.cloudId = cloudId;
        this.ts = ts;
        this.product = product;
    }
}

class Product {
    constructor(id, productName, price, quantity, description, productAvatar) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.productAvatar = productAvatar;
    }
}