import { Modal } from '@mui/material';

function CreateProduct() {
    return (
        <Modal
            open={true}
            // onClose={() => setOpen(false)}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
        >
            <main>
                    <div className="container-fluid px-4">
                        <h1 className="mt-4"></h1>

                        {/* <!-- Content --> */}
                        <div className="row">
                            <div className="col-md-6 col-12 mx-auto">
                                <h1 className="mt-4">Create Product</h1>
                                <form
                                    method="post"
                                    action="/admin/product/create"
                                    modelAttribute="newProduct"
                                >
                                    {/* <!-- Email --> */}
                                    <div className="mb-3 row">
                                        <div className="col">
                                            <label htmlFor="exampleInputEmail1" className="form-label">Name:</label>

                                            <input
                                                type="number"
                                                className="form-control ${not empty priceError ? 'is-invalid' : ''}"
                                                id="exampleInputPassword1"
                                            />

                                           
                                        </div>
                                        {/* <!-- Password --> */}
                                        <div className="col">
                                            <label htmlFor="exampleInputPassword1" className="form-label"
                                                >Price:</label>

                                          

                                            <input
                                                type="number"
                                                className="form-control ${not empty priceError ? 'is-invalid' : ''}"
                                                id="exampleInputPassword1"
                                            />

                                            ${priceError}
                                        </div>
                                    </div>
                                    <!-- Phone -->
                                    <div className="mb-3">
                                        <label for="exampleInputTel" className="form-label"
                                            >Detail description:</label
                                        >
                                        <form:textarea
                                            rows="3"
                                            type="text"
                                            className="form-control"
                                            id="exampleInputTel"
                                            path="detailDesc"
                                        />
                                    </div>

                                    <!-- Full name -->
                                    <div className="row mb-3">
                                        <div className="col">
                                            <label for="exampleInputFullName" className="form-label"
                                                >Short description:</label
                                            >
                                            <form:input
                                                type="text"
                                                className="form-control"
                                                id="exampleInputFullName"
                                                path="shortDesc"
                                            />
                                        </div>
                                        <div className="col">
                                            <label for="exampleInputAddress" className="form-label"
                                                >Quantity:</label
                                            >

                                            <c:set var="quantityError">
                                                <form:errors
                                                    path="quantity"
                                                    cssClass="invalid-feedback"
                                                />
                                            </c:set>

                                            <form:input
                                                className="form-control ${not empty quantityError ? 'is-invalid' : ''}"
                                                id="exampleInputAddress"
                                                path="quantity"
                                            />
                                            ${quantityError}
                                        </div>
                                    </div>

                                    <div className="row mb-3">
                                        <div className="col">
                                            <label for="exampleInputFullName" className="form-label"
                                                >Factory:</label
                                            >
                                            <form:select
                                                className="form-select"
                                                aria-label="Default select example"
                                                path="factory"
                                            >
                                                <form:option value="Apple">Apple</form:option>
                                                <form:option value="Asus">Asus</form:option>
                                                <form:option value="Lenovo">Lenovo</form:option>
                                                <form:option value="Dell">Dell</form:option>
                                                <form:option value="Gigabyte">Gigabyte</form:option>
                                            </form:select>
                                        </div>
                                        <div className="col">
                                            <label for="exampleInputFullName" className="form-label"
                                                >Target:</label
                                            >
                                            <form:select
                                                className="form-select"
                                                aria-label="Default select example"
                                                path="target"
                                            >
                                                <form:option value="Student - office"
                                                    >Student - office</form:option
                                                >
                                                <form:option value="Gaming">Gaming</form:option>
                                            </form:select>
                                        </div>
                                    </div>

                                    <div className="mb-3 row">
                                        <!-- Upload -->
                                        <div className="col-5">
                                            <label for="avatarFile" className="form-label"
                                                >Image:</label
                                            >
                                            <div className="input-group mb-3">
                                                <input
                                                    type="file"
                                                    className="form-control"
                                                    id="avatarFile"
                                                    name="lenamFile"
                                                    accept=".png, .jpg, .jpeg"
                                                />
                                            </div>
                                        </div>
                                        <div className="col-2">
                                            <img
                                                width="100%"
                                                style="height: 69px; display: none"
                                                id="avatarPreview"
                                            />
                                        </div>
                                    </div>

                                    <button type="submit" className="btn btn-primary">Create</button>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </main>

        </Modal>
    );
}

export default CreateProduct;
